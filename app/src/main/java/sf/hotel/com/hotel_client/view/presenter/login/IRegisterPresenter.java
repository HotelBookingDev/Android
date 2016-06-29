package sf.hotel.com.hotel_client.view.presenter.login;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.interfaceeneity.person.IRegisterEntity;
import sf.hotel.com.data.interfaceeneity.person.RegisterEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.StringUtils;
import sf.hotel.com.hotel_client.view.interfaceview.ICallBack;
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
        String pwd = StringUtils.md5(mIRegisterView.getPwd());
        Subscription subscribe = mIRegisterEntity.register(mIRegisterView.getUName(),
                mIRegisterView.getCaptcha(), pwd)
                .subscribe(new SimpleSubscriber<NormalResult>(mIRegisterView.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        failed(ICallBack.REGISTER, e);
                    }

                    @Override
                    public void onNext(NormalResult normalResult) {
                        super.onNext(normalResult);
                        success(ICallBack.REGISTER);
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
                        failed(ICallBack.SMS_CODE, e);
                    }

                    @Override
                    public void onNext(NormalResult normalResult) {
                        super.onNext(normalResult);
                        mIRegisterView.startTimer();
                        success(ICallBack.SMS_CODE);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    public void success(int type) {
        switch (type) {
            case ICallBack.REGISTER:
                mIRegisterView.showViewToast("注册成功");
                mIRegisterView.showLogin();
                break;
            case ICallBack.SMS_CODE:
                mIRegisterView.showViewToast("获取验证码成功");
                break;
        }
    }

    public void failed(int type, Throwable e) {
        if (e instanceof APIException) {
            APIException exception = (APIException) e;
            switch (type) {
                case ICallBack.REGISTER:
                    mIRegisterView.showViewToast(exception.getErrorMessage(mIRegisterView.getBottomContext()));
                    break;
                case ICallBack.SMS_CODE:
                    mIRegisterView.showViewToast(exception.getErrorMessage(mIRegisterView.getBottomContext()));
                    break;
            }
        } else {
            mIRegisterView.showViewToast(e.getMessage());
        }
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

    }

    private void saveUserInfo(String phone, String pwd) {
        mIRegisterEntity.savePhone(mIRegisterView.getBottomContext(), phone);
        mIRegisterEntity.savePwd(mIRegisterView.getBottomContext(), pwd);
    }
}
