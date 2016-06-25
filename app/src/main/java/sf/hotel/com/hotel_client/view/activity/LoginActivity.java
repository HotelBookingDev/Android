package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.SaveCallback;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;

import rx.Subscriber;
import rx.Subscription;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.HotelApplication;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.locationoptions.LocationService;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.LoginMessage;

public class LoginActivity extends BaseActivity {
    private LocationService locationService;

    //提示的次数
    private int isPrompt = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init(savedInstanceState);
        onRxEvent();
        saveIntallationId();
    }

    private void saveIntallationId() {
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                postIntallationId();
            }

            private void postIntallationId() {
                String installationId = PreferencesUtils.getInstallationId(LoginActivity.this);
                //每次登陆判断本地的设备id和初始化的id 是否是同一个不相同就发送一次请求信息
                if (installationId == null ||
                        !installationId.equals(
                                AVInstallation.getCurrentInstallation().getInstallationId())) {
                    ApiWrapper.getInstance()
                            .postIntallation(new Intallation("android",
                                    AVInstallation.getCurrentInstallation().getInstallationId()))
                            .subscribe(new Subscriber<NormalResult>() {
                                @Override
                                public void onCompleted() {
                                    PreferencesUtils.saveInstallationId(LoginActivity.this,
                                            AVInstallation.getCurrentInstallation()
                                                    .getInstallationId());
                                }

                                @Override
                                public void onError(Throwable e) {
                                    if (e instanceof APIException) {
                                        if (((APIException) e).getCode() ==
                                                Code.INTALLATIONIDISEXIT) {
                                            PreferencesUtils.saveInstallationId(LoginActivity.this,
                                                    AVInstallation.getCurrentInstallation()
                                                            .getInstallationId());
                                        }
                                    }
                                }

                                @Override
                                public void onNext(NormalResult normalResult) {

                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(LoginMessage.class)
                .subscribe(loginMessage -> {
                    switch (loginMessage.what) {
                        case LoginMessage.SHOW_MAIN:
                            if (getIntent() == null ||
                                    getIntent().getBooleanExtra("isFinish", false)) {
                                finish();
                            } else {
                                startActivity(MainActivity.class);
                            }
                            break;
                        case LoginMessage.SHOW_REGIST:
                            showFragment(FragConstant.REGISTER);
                            break;
                        case LoginMessage.FRAGMENT_BACK:
                            onBackPressed();
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    private void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.login_fragment, getFragmentByKey(FragConstant.LOGIN));
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // -----------location config ------------
        locationService = ((HotelApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();// 定位SDK
    }

    private BDLocationListener mListener = location -> {
        if (null != location && location.getLocType() != BDLocation.TypeServerError) {
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            /**
             * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
             * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
             */
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            sb.append("\nCountryCode : ");
            sb.append(location.getCountryCode());
            sb.append("\nCountry : ");
            sb.append(location.getCountry());
            sb.append("\ncitycode : ");
            sb.append(location.getCityCode());
            sb.append("\ncity : ");
            sb.append(location.getCity());
            sb.append("\nDistrict : ");
            sb.append(location.getDistrict());
            sb.append("\nStreet : ");
            sb.append(location.getStreet());
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\nDescribe: ");
            sb.append(location.getLocationDescribe());
            sb.append("\nDirection(not all devices have value): ");
            sb.append(location.getDirection());
            sb.append("\nPoi: ");
            if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                for (int i = 0; i < location.getPoiList().size(); i++) {
                    Poi poi = location.getPoiList().get(i);
                    sb.append(poi.getName() + ";");
                }
            }
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：km/h
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");
                PreferencesUtils.saveCityCode(this, location.getCityCode());
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                // 运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
                PreferencesUtils.saveCityCode(this, location.getCityCode());
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            showPrompt(location.getLocType());
        }
    };

    private void showPrompt(int type) {
        if (isPrompt == -1) {
            if (type == BDLocation.TypeNetWorkException) {
                showToast("当前网络不稳定");
            } else if (type == BDLocation.TypeOffLineLocation) {
                if (!checkConnection(this)) {
                    showToast("当前网络状态无");
                } else {
                    showToast("定位权限未打开");
                }
            }
            isPrompt = 1;
        }
    }
}
