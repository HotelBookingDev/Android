package sf.hotel.com.hotel_client.view.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.interfaceeneity.ILoginEntity;
import sf.hotel.com.data.interfaceeneity.LoginEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.view.interfaceview.ICallBack;
import sf.hotel.com.hotel_client.view.interfaceview.ILoginView;

/**
 * Created by FMT on 2016/6/3:18:54
 * EMAILE 1105896230@qq.com.
 */
public class ILoginPresenter extends SuperPresenter {
    private ILoginView mILoginView;
    private ILoginEntity mILoginEntity;

    private CompositeSubscription mCompositeSubscription;

    public ILoginPresenter(ILoginView mILoginView) {
        this.mILoginView = mILoginView;
        mILoginEntity = new LoginEntityImp();
        mCompositeSubscription = new CompositeSubscription();
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
            if (i == Code.LOGIN_FORMAT_ERROR) {
                mILoginView.clearUserName();
                mILoginView.clearPassword();
                mILoginView.showViewToast(getErrorString(msgid, mILoginView.getBottomContext()));
            } else if (i == Code.LOGIN_NAME_NULL) {
                mILoginView.showViewToast(getErrorString(msgid, mILoginView.getBottomContext()));
            } else if (i == Code.LOGIN_PWD_NULL) {
                mILoginView.showViewToast(getErrorString(msgid, mILoginView.getBottomContext()));
            } else if (i == Code.LOGIN_PWD_ERROR) {
                if (msgid == 0) {
                    mILoginView.showViewToast(e.getMessage());
                }
            }
        }
    }

    public void login() {

        Subscription subscribe = mILoginEntity.login(mILoginView.getUserName(),
                mILoginView.getPassword())
                .subscribe(new SimpleSubscriber<UserEntity>(mILoginView.getBottomContext()) {
                    @Override
                    public void onNext(UserEntity loginResult) {
                        super.onNext(loginResult);
                        mILoginView.success(ICallBack.LOGIN);
                        LogUtils.e("test", loginResult.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        handlingException(e);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }
}
