package sf.hotel.com.hotel_client.view.presenter.hotel;

import rx.Subscription;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.data.interfaceeneity.hotel.IRoomEntityImp;
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

    public IRoomPresenter(IRoomView mIRoomView) {
        super();
        this.mIRoomView = mIRoomView;
        mIRoomEntityImp = new IRoomEntityImp();
    }

    public void callHotelBook() {
        Subscription subscribe = mIRoomEntityImp.callHotelBook("", "", "", "")
                .subscribe(new SimpleSubscriber<HotelBookResult>(mIRoomView.getBottomContext()) {
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

    public void callHotelBean() {
        Subscription subscribe = mIRoomEntityImp.callHotelBean(
                String.valueOf(mIRoomView.getHotelId()))
                .subscribe(new SimpleSubscriber<HotelsBean>(mIRoomView.getBottomContext()) {
                    @Override
                    public void onNext(HotelsBean hotelsBean) {
                        super.onNext(hotelsBean);
                        onNextHotelBean(hotelsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
        addSubsrcicitpition(subscribe);
    }

    private void onNextHotelBean(HotelsBean hotelsBean) {
        mIRoomView.setHotelsBean(hotelsBean);
    }
}
