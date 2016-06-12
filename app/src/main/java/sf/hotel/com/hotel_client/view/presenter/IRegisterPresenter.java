package sf.hotel.com.hotel_client.view.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.interfaceeneity.IRegisterEntity;
import sf.hotel.com.data.interfaceeneity.RegisterEntityImp;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.hotel_client.view.interfaceview.IRegisterView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public class IRegisterPresenter extends SuperPresenter {
    IRegisterView mIRegisterView;

    IRegisterEntity mIRegisterEntity;
    CompositeSubscription mCompositeSubscription;

    public IRegisterPresenter(IRegisterView mIRegisterView) {
        this.mIRegisterView = mIRegisterView;
        mIRegisterEntity = new RegisterEntityImp();
        mCompositeSubscription = new CompositeSubscription();
    }

    public void register() {
        Subscription subscribe = mIRegisterEntity.register(mIRegisterView.getUName(),
                mIRegisterView.getCaptcha(), mIRegisterView.getPwd())
                .subscribe(new SimpleSubscriber<NormalResult>(mIRegisterView.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mIRegisterView.failed(2);
                    }

                    @Override
                    public void onNext(NormalResult normalResult) {
                        super.onNext(normalResult);
                        mIRegisterView.success(2);
                        mIRegisterView.startTimer();
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
                        mIRegisterView.failed(3);
                    }

                    @Override
                    public void onNext(NormalResult normalResult) {
                        super.onNext(normalResult);
                        mIRegisterView.success(3);
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

    }
}
