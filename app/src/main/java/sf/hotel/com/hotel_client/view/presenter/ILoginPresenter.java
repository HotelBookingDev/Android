package sf.hotel.com.hotel_client.view.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.interfaceeneity.ILoginEntity;
import sf.hotel.com.data.interfaceeneity.LoginEntityImp;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.hotel_client.view.interfaceview.ILoginView;

/**
 * Created by FMT on 2016/6/3:18:54
 * EMAILE 1105896230@qq.com.
 */
public class ILoginPresenter implements Presenter {
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

    public void login() {

        Subscription subscribe = mILoginEntity.login(mILoginView.getUserName(),
                mILoginView.getPassword())
                .subscribe(new SimpleSubscriber<LoginResult>(mILoginView.getBottomContext()) {
                    @Override
                    public void onNext(LoginResult loginResult) {
                        super.onNext(loginResult);
                        mILoginView.success(1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mILoginView.failed(1);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }
}
