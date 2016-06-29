package sf.hotel.com.hotel_client.view.presenter.hotel;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.data.interfaceeneity.ICityEntityImp;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.CityMessage;
import sf.hotel.com.hotel_client.view.event.hotel.MessageFactory;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ICityView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ICityPresenter extends SuperPresenter {
    private ICityView mICityView;

    private ICityEntityImp mICityEntityImp;

    private CompositeSubscription mCompositeSubscription;

    public ICityPresenter(ICityView mICityView) {
        this.mICityView = mICityView;
        mCompositeSubscription = new CompositeSubscription();
        mICityEntityImp = new ICityEntityImp();
    }

    public ProcincesResult getProcincesResult(Context context){
        return mICityEntityImp.getProcincesResult(context);
    }

    public void saveCitysBean(Context context, ProcincesResult.ProcincesBean.CitysBean citysBean){
        mICityEntityImp.saveCitysBean(context, citysBean);
    }


    public void callCityList() {
        Subscription subscribe = mICityEntityImp.callCityList()
                .subscribe(new SimpleSubscriber<ProcincesResult>(mICityView.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        RxBus.getDefault()
                                .post(MessageFactory.createCityMessage(CityMessage.FAILE, e));
                    }

                    @Override
                    public void onNext(ProcincesResult procincesResult) {
                        super.onNext(procincesResult);
                        RxBus.getDefault()
                                .post(MessageFactory.createCityMessage(CityMessage.SUCCESS,
                                        procincesResult));
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    @Override
    public void destroy() {
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
