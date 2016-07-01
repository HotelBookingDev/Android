package sf.hotel.com.hotel_client.view.activity.register;

import android.os.Bundle;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.SaveCallback;

import java.util.HashMap;

import rx.Subscriber;
import rx.Subscription;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.LocationHelper;
import sf.hotel.com.hotel_client.utils.transulcent.TransulcentUtils;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.activity.MainActivity;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.fragment.login.LoginFragment;
import sf.hotel.com.hotel_client.view.fragment.login.RegisterFragment;

public class LoginActivity extends BaseActivity {

    private LocationHelper locationHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init(savedInstanceState);
        onRxEvent();
        saveIntallationId();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TransulcentUtils.setFixWindow(this);
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
                            start(RegisterFragment.newInstance());
                            break;
                        case LoginMessage.FRAGMENT_BACK:
                            onBackPressed();
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    private void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.login_fragment, LoginFragment.newInstance());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationHelper.stop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationHelper = new LocationHelper(new LocationHelper.LocationListener() {
            @Override
            public void onGetLocation(HashMap map) {
                String cityCode = (String) map.get(LocationHelper.CITYCODE);
                String cityCityName = (String) map.get(LocationHelper.CITYNAME);
                PreferencesUtils.saveCityCode(LoginActivity.this, cityCode);
                PreferencesUtils.saveCityName(LoginActivity.this, cityCityName);
            }

            @Override
            public void onError(int type) {
                showPrompt(type);
            }
        });
        locationHelper.start();
    }
}
