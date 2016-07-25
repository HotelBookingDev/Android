package sf.hotel.com.hotel_client.view.presenter.login;

import android.content.Context;

import rx.Observable;
import rx.Subscription;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.interfaceeneity.login.ILRCommend;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.net.callback.CommSubscriber;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.view.interfaceview.login.ILRConmView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public class ILRcomPresenter extends SuperPresenter {

    protected void saveUserInfo(ILRCommend commend, Context context, String phone,
                                String avatar, String id) {
        commend.savePhone(context, phone);
        commend.saveAvatar(context, avatar);
        commend.saveUserId(context, id);
    }

    //登录或者注册成功调用的方法
    protected void suceess(LoginResult loginResult, ILRCommend commend, ILRConmView view) {
        //保存用户信息
        Subscription subscribe = Observable.just(loginResult)
                .filter(loginResult1 -> loginResult1 == null ? Boolean.FALSE : Boolean.TRUE)
                .filter(loginResult1 -> loginResult1.getUserEntity() ==
                        null ? Boolean.FALSE : Boolean.TRUE)
                .doOnNext(loginResult1 -> saveUserInfo(commend, view.getBottomContext(),
                        view.getPhoneNum(), loginResult.getUserEntity().getAvatar(),
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
        addSubsrcicitpition(subscribe);
    }

    public void loginError(Throwable throwable, ILRCommend commend, ILRConmView view) {
        if (throwable instanceof APIException) {
//             服务器已经注册验证码
            if (((APIException) throwable).getCode() == Code.INTALLATIONIDNOREGISTR) {
                PreferencesUtils.saveInstallationId(view.getBottomContext(), null);
                Subscription subscribe = postIntallationId(commend, view).subscribe(
                        new CommSubscriber<>());
                addSubsrcicitpition(subscribe);
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
                    .flatMap(normalResult -> commend.postInllation("android", view.getPhoneNum(),
                            view.getIntallationId()));
        } else {
            observable = commend.postInllation("android", view.getPhoneNum(),
                    view.getIntallationId());
        }
        return observable;
    }

    public void callPhoneCaptcha(ILRCommend commend, ILRConmView view, int type) {
        Subscription subscribe = commend.getSmsCode(view.getPhoneNum(), type)
                .subscribe(new SimpleSubscriber<NormalResult>(view.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        handlingException(view, e);
                    }

                    @Override
                    public void onNext(NormalResult normalResult) {
                        super.onNext(normalResult);
                        view.startTimer();
                        view.showViewToast("获取验证码成功");
                    }
                });
        addSubsrcicitpition(subscribe);
    }

    public void handlingException(ILRConmView view, Throwable e) {
        if (e instanceof APIException) {
            int i = ((APIException) e).getCode();
            int msgid = ((APIException) e).getMessageId();
            if (msgid != 0) {
                view.showViewToast(getErrorString(msgid, view.getBottomContext()));
            }
        }
    }
}
