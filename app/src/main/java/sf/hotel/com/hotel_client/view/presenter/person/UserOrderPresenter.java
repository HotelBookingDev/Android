package sf.hotel.com.hotel_client.view.presenter.person;

import rx.Subscription;
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
        super();
        this.mIUserOrderView = mIUserOrderView;
        mIorder = new IOrderImp();
    }

    public void getDatas(int position) {
        getDb(position);
    }

    private void getDb(int position) {
        Subscription subscribe = mIorder.getOrder(mIUserOrderView.getBottomContext(), position)
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
        addSubsrcicitpition(subscribe);
    }

    public void detect(Order order) {
        Subscription subscribe = mIorder.detect(order).subscribe(normalResult -> {
            order.setClosed(true);
            mIorder.update(mIUserOrderView.getBottomContext(), order);
            mIorder.getOrder(mIUserOrderView.getBottomContext(), mIUserOrderView.getPosition())
                    .subscribe(list -> {
                        mIUserOrderView.showOrder(list);
                    });
            mIUserOrderView.showViewToast("取消成功");
        }, LogUtils::logThrowadle);
        addSubsrcicitpition(subscribe);
    }

    public void refresh(int position) {
        Subscription subscribe = mIorder.forceRefresh(mIUserOrderView.getBottomContext(), position)
                .subscribe(orderAndHotels -> {
//                    异步加载完成判断当前显示是否是开始点击时候需要查看的订单列表
                    if (mIUserOrderView.getPosition() == position) {
                        mIUserOrderView.showOrder(orderAndHotels);
                    }
                    mIUserOrderView.pullViewComplete();
                }, this::handlingException);
        addSubsrcicitpition(subscribe);
    }
}
