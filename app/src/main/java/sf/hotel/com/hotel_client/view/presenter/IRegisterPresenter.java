package sf.hotel.com.hotel_client.view.presenter;

import rx.Subscriber;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.interfaceeneity.IRegisterEntity;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.hotel_client.utils.TToast;
import sf.hotel.com.hotel_client.view.interfaceview.IRegisterView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public class IRegisterPresenter implements Presenter {
    IRegisterView mIRegisterView;

    IRegisterEntity mIRegisterEntity;

    public IRegisterPresenter(IRegisterView mIRegisterView) {
        this.mIRegisterView = mIRegisterView;
    }

    public void register(){
        mIRegisterEntity.register(mIRegisterView.getUName(),
                mIRegisterView.getCaptcha(),
                mIRegisterView.getPwd())
                .subscribe(new SimpleSubscriber<NormalResult>(mIRegisterView.getBottomContext()){
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mIRegisterView.failed(2);
                    }

                    @Override
                    public void onNext(NormalResult normalResult) {
                        super.onNext(normalResult);
                        mIRegisterView.success(2);
                    }
                });
    }

    public void callPhoneCaptcha(){
        mIRegisterEntity.getSmsCode(mIRegisterView.getUName())
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
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
