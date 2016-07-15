package sf.hotel.com.hotel_client.view.presenter.hotel;

import rx.Subscription;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.interfaceeneity.hotel.IBookingEntityImp;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IBookingView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public class IBookingPresenter extends SuperPresenter {
    IBookingEntityImp mIBookingEntityImp;
    IBookingView mIBookingView;

    public IBookingPresenter(IBookingView mIBookingView) {
        this.mIBookingView = mIBookingView;
        mIBookingEntityImp = new IBookingEntityImp();
    }


    public void callHotelBook() {
        Subscription subscribe = mIBookingEntityImp.callHotelBook("", "", "")
                .subscribe(new SimpleSubscriber<HotelBookResult>(mIBookingView.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(HotelBookResult hotelBookResult) {
                        super.onNext(hotelBookResult);
                    }
                });
        addSubsrcicitpition(subscribe);
    }

}
