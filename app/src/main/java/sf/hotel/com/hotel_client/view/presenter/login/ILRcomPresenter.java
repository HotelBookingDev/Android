package sf.hotel.com.hotel_client.view.presenter.login;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.interfaceeneity.login.ILRCommend;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.HotelApplication;
import sf.hotel.com.hotel_client.view.interfaceview.login.ILRConmView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public class ILRcomPresenter extends SuperPresenter {
    @Override
    public void destroy() {

    }

    protected void saveUserInfo(ILRCommend commend, Context context, String phone, String pwd,
            String avatar, String id) {
        commend.savePhone(context, phone);
        commend.savePwd(context, pwd);
        commend.saveAvatar(context, avatar);
        commend.saveUserId(context, id);
    }

    //登录或者注册成功调用的方法
    protected void suceess(LoginResult loginResult, ILRCommend commend, ILRConmView view,
            String pwd) {
        //保存用户信息
        Observable.just(loginResult)
                .filter(loginResult1 -> loginResult1 == null ? Boolean.FALSE : Boolean.TRUE)
                .filter(loginResult1 -> loginResult1.getUserEntity() ==
                        null ? Boolean.FALSE : Boolean.TRUE)
                .doOnNext(loginResult1 -> saveUserInfo(commend, view.getBottomContext(),
                        view.getUserName(), pwd, loginResult.getUserEntity().getAvatar(),
                        String.valueOf(loginResult.getUserEntity().getUserId())))
                .doOnNext(loginResult1 -> commend.upDateUserInfo(view.getBottomContext(),
                        loginResult1.getUserEntity()))
                .doOnNext(loginResult1 -> EntityContext.getInstance()
                        .setmCurrentUser(loginResult1.getUserEntity()))
//                上传设备号
                .flatMap(loginResult1 -> postIntallationId(commend, view))
                .subscribe(loginResult1 -> {
                    view.startHomeActivity();
                }, throwable -> {
                    loginError(throwable, commend, view);
                });
    }

    public void loginError(Throwable throwable, ILRCommend commend, ILRConmView view) {
        if (throwable instanceof APIException) {
//             服务器为注册验证码
            if (((APIException) throwable).getCode() == Code.INTALLATIONIDNOREGISTR) {
                PreferencesUtils.saveInstallationId(view.getBottomContext(), null);
                postIntallationId(commend,view);
            }
        }
        LogUtils.logThrowadle(throwable);
    }

    protected Observable<NormalResult> postIntallationId(ILRCommend commend, ILRConmView view) {
        Observable<NormalResult> observable = null;
        String id = PreferencesUtils.getInstallationId(view.getBottomContext());
        if (id == null) {
            observable = commend.postIntallation(
                    new Intallation("android", view.getIntallationId()))
                    .flatMap(normalResult -> commend.postInllation("android", view.getUserName(),
                            view.getIntallationId()));
        } else {
            observable = commend.postInllation("android", view.getUserName(),
                    view.getIntallationId());
        }
        return observable;
    }
}
