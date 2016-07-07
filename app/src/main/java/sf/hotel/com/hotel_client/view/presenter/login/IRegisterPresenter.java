package sf.hotel.com.hotel_client.view.presenter.login;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.interfaceeneity.person.IRegisterEntity;
import sf.hotel.com.data.interfaceeneity.person.RegisterEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.StringUtils;
import sf.hotel.com.hotel_client.view.interfaceview.login.IRegisterView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public class IRegisterPresenter extends SuperPresenter {
    private IRegisterView mIRegisterView;

    private IRegisterEntity mIRegisterEntity;
    private CompositeSubscription mCompositeSubscription;

    public IRegisterPresenter(IRegisterView mIRegisterView) {
        this.mIRegisterView = mIRegisterView;
        mIRegisterEntity = new RegisterEntityImp();
        mCompositeSubscription = new CompositeSubscription();
    }

    public void register() {
        //注册使用md5
        String pwd = StringUtils.changePud(mIRegisterView.getPwd());
        Subscription subscribe = mIRegisterEntity.register(mIRegisterView.getUName(),
                mIRegisterView.getCaptcha(), pwd)
                .subscribe(new SimpleSubscriber<LoginResult>(mIRegisterView.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        handlingException(e);
                    }

                    @Override
                    public void onNext(LoginResult normalResult) {
                        super.onNext(normalResult);

                        mIRegisterView.showViewToast("注册成功");
                        mIRegisterView.showLogin();

                        //传递md5的数值
                        saveUserInfo(mIRegisterView.getUName(), pwd);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    public void callPhoneCaptcha() {
        Subscription subscribe = mIRegisterEntity.getSmsCode(mIRegisterView.getUName())
                .subscribe(new SimpleSubscriber<NormalResult>(mIRegisterView.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        handlingException(e);
                    }

                    @Override
                    public void onNext(NormalResult normalResult) {
                        super.onNext(normalResult);
                        mIRegisterView.startTimer();
                        mIRegisterView.showViewToast("获取验证码成功");
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mCompositeSubscription.unsubscribe();
    }

    @Override
    public void handlingException(Throwable e) {
        if (e instanceof APIException) {
            int i = ((APIException) e).getCode();
            int msgid = ((APIException) e).getMessageId();
            if (msgid == 0) {
                mIRegisterView.showViewToast(e.getMessage());
            } else {
                mIRegisterView.showViewToast(
                        getErrorString(msgid, mIRegisterView.getBottomContext()));
            }
        } else {
            mIRegisterView.showViewToast(e.getMessage());
        }
    }

    private void saveUserInfo(String phone, String pwd) {
        mIRegisterEntity.savePhone(mIRegisterView.getBottomContext(), phone);
        mIRegisterEntity.savePwd(mIRegisterView.getBottomContext(), pwd);
    }
}
