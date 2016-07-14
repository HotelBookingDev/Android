package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import java.util.List;

import rx.Observable;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.entity.OrderManager;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.entity.netresult.person.OrderManagerResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.callback.CommSubscriber;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class IOrderImp implements IOrder {
    OrderManager mOrderManager;

    private boolean isDownLoad = false;

    public IOrderImp() {
        mOrderManager = new OrderManager();
    }

    @Override
    public Observable<List<Order>> getOrder(Context context, int position) {
        return Observable.just(mOrderManager.getOrders(context, position,
                EntityContext.getInstance().getmCurrentUser().getUserId()));
    }

    public Observable<List<Order>> getOrderByNet(Context context, int position) {
        if (isDownLoad) {
            return Observable.just(mOrderManager.getOrders(context, position,
                    EntityContext.getInstance().getmCurrentUser().getUserId()));
        }
        return ApiWrapper.getInstance()
                .getOrderManager()
                .filter(orderManagerResult -> orderManagerResult ==
                        null ? Boolean.FALSE : Boolean.TRUE)
                .map(OrderManagerResult::getManager)
                .doOnNext(orderManager -> mOrderManager.saveDb(context, orderManager,
                        EntityContext.getInstance().getmCurrentUser().getUserId()))
                .doOnNext(orderManager -> mOrderManager.update(context, orderManager,
                        EntityContext.getInstance().getmCurrentUser().getUserId()))
                .map(orderManager -> mOrderManager.getOrders(context, position,
                        EntityContext.getInstance().getmCurrentUser().getUserId()))
                .doOnNext(list -> {
                    isDownLoad = true;
                });
    }

    @Override
    public Observable<NormalResult> detect(Order order) {
        return ApiWrapper.getInstance().deleteOrder(order.getOrder_num());
    }

    @Override
    public void update(Context context, Order order) {
        mOrderManager.update(context, order);
        mOrderManager.setmNotOrders(null);
        mOrderManager.setmAlreadyOrders(null);
        getOrder(context, Order.ALRADYORDER).subscribe(new CommSubscriber<>());
    }

    @Override
    public Observable<List<Order>> forceRefresh(Context context, int position) {
        isDownLoad = false;
        return getOrderByNet(context, position);
    }
}
