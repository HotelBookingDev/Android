package sf.hotel.com.hotel_client.view.presenter.hotel;

import android.content.Context;

import java.util.Date;

import rx.Subscription;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.SearchItem;
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

        SearchItem searchItem = mIBookingView.getSearchItem();


        Subscription subscribe = mIBookingEntityImp.callHotelBook("1",searchItem)
                .subscribe(new SimpleSubscriber<HotelBookResult>(mIBookingView.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        handlingException(e);
                    }

                    @Override
                    public void onNext(HotelBookResult hotelBookResult) {
                        super.onNext(hotelBookResult);


                    }
                });
        addSubsrcicitpition(subscribe);
    }


    public void getSearchItem() {
        SearchItem searchItem = mIBookingEntityImp.getSearchItem(mIBookingView.getBottomContext());
        if (searchItem == null){
            searchItem = new SearchItem();
        }
        mIBookingView.setSearchItem(searchItem);
    }

    public void saveSearchItem() {
        mIBookingEntityImp.saveSearchItem(mIBookingView.getBottomContext(),
                mIBookingView.getSearchItem());
    }


}
