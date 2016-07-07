package sf.hotel.com.hotel_client.view.presenter.login;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.SaveCallback;

import java.util.HashMap;

import rx.Subscriber;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.LocationHelper;
import sf.hotel.com.hotel_client.view.interfaceview.login.ISplashView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public class ISplashPresenter extends SuperPresenter {
    private LocationHelper locationHelper;

    public ISplashPresenter(ISplashView view) {
        this.view = view;
    }

    ISplashView view;

    @Override
    public void destroy() {
        if (locationHelper == null) return;
        locationHelper.stop();
    }

    //TODO 如果采用服务器更新的方式需要做数据缓存判断 默认使用固定个图片每次更新版本才换
    public Object getImage() {
        return R.mipmap.splash;
    }

    //定位&&发送设备
    public void initDatas() {
        locationHelper = new LocationHelper(new LocationHelper.LocationListener() {
            @Override
            public void onGetLocation(HashMap map) {
                String cityCode = (String) map.get(LocationHelper.CITYCODE);
                String cityCityName = (String) map.get(LocationHelper.CITYNAME);
                PreferencesUtils.saveCityCode(view.getBottomContext(), cityCode);
                PreferencesUtils.saveCityName(view.getBottomContext(), cityCityName);
                LogUtils.e("cityCode", cityCode + "");
                LogUtils.e("cityCityName", cityCityName + "");
            }

            @Override
            public void onError(int type) {
                LogUtils.e("location_error", type + "");
                view.showPrompt(type);
            }
        });
        locationHelper.start();
        saveIntallationId();
    }

    private void saveIntallationId() {
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                postIntallationId();
            }

            private void postIntallationId() {
                String installationId = PreferencesUtils.getInstallationId(view.getBottomContext());
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
                                    PreferencesUtils.saveInstallationId(view.getBottomContext(),
                                            AVInstallation.getCurrentInstallation()
                                                    .getInstallationId());
                                    view.startLoginActivity();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    if (e instanceof APIException) {
                                        if (((APIException) e).getCode() ==
                                                Code.INTALLATIONIDISEXIT) {
                                            PreferencesUtils.saveInstallationId(
                                                    view.getBottomContext(),
                                                    AVInstallation.getCurrentInstallation()
                                                            .getInstallationId());
                                        }
                                    }
                                    view.startLoginActivity();
                                }

                                @Override
                                public void onNext(NormalResult normalResult) {

                                }
                            });
                } else {
                    view.startLoginActivity();
                }
            }
        });
    }
}
