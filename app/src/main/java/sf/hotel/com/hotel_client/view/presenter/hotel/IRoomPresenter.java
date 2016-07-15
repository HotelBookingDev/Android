package sf.hotel.com.hotel_client.view.presenter.hotel;

import java.util.List;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.netresult.hotel.Hotel1Result;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.data.interfaceeneity.hotel.IRoomEntityImp;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IRoomView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/16.
 */
public class IRoomPresenter extends SuperPresenter {
    private IRoomView mIRoomView;

    private IRoomEntityImp mIRoomEntityImp;

    CompositeSubscription mCompositeSubscription;

    public IRoomPresenter(IRoomView mIRoomView) {
        this.mIRoomView = mIRoomView;
        mIRoomEntityImp = new IRoomEntityImp();

        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void destroy() {
        if (mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }


    public void callHotelBook(){
        mIRoomEntityImp.callHotelBook("", "", "", "")
                .subscribe(new SimpleSubscriber<HotelBookResult>(mIRoomView.getBottomContext()){
            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(HotelBookResult hotelBookResult) {
                super.onNext(hotelBookResult);
            }
        });
    }


    public void callHotelBean(){
        mIRoomEntityImp.callHotelBean(String.valueOf(mIRoomView.getHotelId()))
                .subscribe(new SimpleSubscriber<Hotel1Result>(mIRoomView.getBottomContext()){
                    @Override
                    public void onNext(Hotel1Result hotel1Result) {
                        super.onNext(hotel1Result);
                        onNextHotelBean(hotel1Result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    private void onNextHotelBean(Hotel1Result hotelsBean) {
        mIRoomView.setHotelsBean(hotelsBean.getHotel());
    }

}
