package sf.hotel.com.hotel_client.view.presenter.person;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.interfaceeneity.person.IOrder;
import sf.hotel.com.data.interfaceeneity.person.IOrderImp;
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
    private final List<Integer> list = new ArrayList();

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
                .doOnNext(orderAndHotels -> mIUserOrderView.showOrder(orderAndHotels))
                .flatMap(new Func1<List<Order>, Observable<List<Order>>>() {
                    @Override
                    public Observable<List<Order>> call(List<Order> orders) {
//
                        if (list.contains(position)) {
                            return Observable.just(orders);
                        }
                        list.add(position);
                        return mIorder.loadDatas(mIUserOrderView.getBottomContext(), position);
                    }
                })
                .subscribe(orderAndHotels -> {
//                    异步加载完成判断当前显示是否是开始点击时候需要查看的订单列表
                    if (mIUserOrderView.getPosition() == position) {
                        mIUserOrderView.showOrder(orderAndHotels);
                    }
                }, this::handlingException);
        addSubsrcicitpition(subscribe);
    }

    public void detect(Order order) {
        Subscription subscribe = mIorder.detect(mIUserOrderView.getBottomContext(), order, mIUserOrderView.getPosition()).subscribe(orders -> {
            mIUserOrderView.showOrder(orders);
            mIUserOrderView.showViewToast("取消成功");
        }, this::handlingException);
        addSubsrcicitpition(subscribe);
    }

    public void refresh(int position) {
        Subscription subscribe = mIorder.loadDatas(mIUserOrderView.getBottomContext(), position)
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
