package sf.hotel.com.hotel_client.view.presenter.person;

import android.util.Log;

import java.util.List;

import rx.functions.Action1;
import sf.hotel.com.data.entity.OrderAndHotel;
import sf.hotel.com.data.interfaceeneity.person.IOrder;
import sf.hotel.com.data.interfaceeneity.person.IOrderImp;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.view.interfaceview.person.IUserOrderView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class UserOrderPresenter extends SuperPresenter{
    IUserOrderView mIUserOrderView;
    IOrder mIorder;

    public UserOrderPresenter(IUserOrderView mIUserOrderView) {
        this.mIUserOrderView = mIUserOrderView;
        mIorder = new IOrderImp();
    }

    public void getDatas(int position) {
//        getDb(position);
        getNet(position);
    }

    private void getNet(int poistion){
        mIorder.getOrderByNet(mIUserOrderView.getBottomContext(),poistion).subscribe(
                orderAndHotels -> {
                    LogUtils.d("test");
                }, throwable -> {
            LogUtils.d("error",throwable.getMessage());
        });
    }
    private void getDb(int position) {
        mIorder.getOrderByDb(mIUserOrderView.getBottomContext(), position)
                .doOnNext(orderAndHotels -> {
                    mIUserOrderView.showOrder(orderAndHotels);
                })
                .flatMap(orderAndHotels -> mIorder.getOrderByNet(mIUserOrderView.getBottomContext(),
                        position))
                .subscribe(orderAndHotels -> {
                    mIUserOrderView.showOrder(orderAndHotels);
                }, this::handlingException);
    }

    @Override
    public void destroy() {

    }
}
