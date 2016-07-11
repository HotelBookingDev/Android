package sf.hotel.com.hotel_client.view.presenter.login;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.SaveCallback;

import java.util.HashMap;

import rx.Subscriber;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.interfaceeneity.login.ISplahImp;
import sf.hotel.com.data.interfaceeneity.login.ISplashEntity;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.utils.CheckUtils;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.LocationHelper;
import sf.hotel.com.hotel_client.view.activity.SplashActivity;
import sf.hotel.com.hotel_client.view.interfaceview.login.ISplashView;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public class ISplashPresenter extends ILRcomPresenter {
    private LocationHelper locationHelper;

    public ISplashPresenter(ISplashView view) {
        this.view = view;
        entity = new ISplahImp();
    }

    ISplashView view;

    ISplashEntity entity;

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
                entity.saveCityCode(view.getBottomContext(), cityCode);
                entity.saveCityName(view.getBottomContext(), cityCityName);
            }

            @Override
            public void onError(int type) {
                view.showPrompt(type);
            }
        });
        locationHelper.start();
        saveIntallationId();
    }

    //发送设备号
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
                    postInstallId();
                } else {
//                    在本地一致的情况下发送自动登录的账号密码
                    autoLogin();
                }
            }
        });
    }

    private void autoLogin() {
        String phone = entity.getPhone(view.getBottomContext());
        String pwd = entity.getPwd(view.getBottomContext());
//        存在本地不是真实密码不需要比对
        if (!CheckUtils.isTextViewEmpty(phone) && !CheckUtils.isTextViewEmpty(pwd)) {
            entity.login(phone, pwd).subscribe(loginResult -> {
                suceess(loginResult, entity, view, pwd);
            }, this::handlingException);
        } else {
            view.startActivity(SplashActivity.LOGIN);
        }
    }

    //    重下登录成功保存信息出错
    @Override
    public void loginError(Throwable throwable) {
        super.loginError(throwable);
        view.startActivity(SplashActivity.LOGIN);
    }

    @Override
    public void handlingException(Throwable e) {
        LogUtils.logThrowadle(e);
        view.startActivity(SplashActivity.LOGIN);
    }

    private void postInstallId() {

        ApiWrapper.getInstance()
                .postIntallation(new Intallation("android",
                        AVInstallation.getCurrentInstallation().getInstallationId()))
                .subscribe(new Subscriber<NormalResult>() {
                    @Override
                    public void onCompleted() {
                        PreferencesUtils.saveInstallationId(view.getBottomContext(),
                                AVInstallation.getCurrentInstallation().getInstallationId());
                        view.startActivity(SplashActivity.LOGIN);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof APIException) {
                            if (((APIException) e).getCode() == Code.INTALLATIONIDISEXIT) {
                                PreferencesUtils.saveInstallationId(view.getBottomContext(),
                                        AVInstallation.getCurrentInstallation()
                                                .getInstallationId());
                            }
                        }
                        view.startActivity(SplashActivity.LOGIN);
                    }

                    @Override
                    public void onNext(NormalResult normalResult) {

                    }
                });
    }

    public String getUserName() {
        return entity.getUserName(view.getBottomContext());
    }
}
