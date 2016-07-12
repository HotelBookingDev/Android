package sf.hotel.com.hotel_client.view.presenter.person;

import sf.hotel.com.data.entity.Order;
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
public class UserOrderPresenter extends SuperPresenter {
    IUserOrderView mIUserOrderView;
    IOrder mIorder;

    public UserOrderPresenter(IUserOrderView mIUserOrderView) {
        this.mIUserOrderView = mIUserOrderView;
        mIorder = new IOrderImp();
    }

    public void getDatas(int position) {
        getDb(position);
    }

    private void getDb(int position) {
        mIorder.getOrderByDb(mIUserOrderView.getBottomContext(), position)
                .doOnNext(orderAndHotels -> {
                    mIUserOrderView.showOrder(orderAndHotels);
                })
                .flatMap(orderAndHotels -> mIorder.getOrderByNet(mIUserOrderView.getBottomContext(),
                        position))
                .subscribe(orderAndHotels -> {
//                    异步加载完成判断当前显示是否是开始点击时候需要查看的订单列表
                    if (mIUserOrderView.getPosition() == position) {
                        mIUserOrderView.showOrder(orderAndHotels);
                    }
                }, this::handlingException);
    }

    @Override
    public void destroy() {

    }

    public void detect(Order order) {
        mIorder.detect(order).subscribe(normalResult -> {
            mIUserOrderView.showViewToast("取消成功");
        }, LogUtils::logThrowadle);
    }
}
