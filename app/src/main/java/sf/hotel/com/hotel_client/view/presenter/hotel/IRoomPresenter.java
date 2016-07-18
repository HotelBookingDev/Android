package sf.hotel.com.hotel_client.view.presenter.hotel;

import rx.Subscription;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.netresult.hotel.Hotel1Result;
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

    public void callHotelBean(){
        String ex = "";

        Subscription subscribe = mIRoomEntityImp.callHotelBean(String.valueOf(mIRoomView.getHotelId()), ex)
                .subscribe(new SimpleSubscriber<Hotel1Result>(mIRoomView.getBottomContext()) {

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
        addSubsrcicitpition(subscribe);
    }

    private void onNextHotelBean(Hotel1Result hotelsBean) {
        mIRoomView.setHotelsBean(hotelsBean.getHotel());
    }
}
