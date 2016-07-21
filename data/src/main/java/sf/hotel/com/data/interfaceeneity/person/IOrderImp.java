package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.datasource.OrderDao;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.entity.OrderManagerMaps;
import sf.hotel.com.data.entity.netresult.person.OrderListsResult;
import sf.hotel.com.data.entity.netresult.person.OrderReuslt;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class IOrderImp implements IOrder {
    OrderManagerMaps mOrderManager;


    public IOrderImp() {
        mOrderManager = new OrderManagerMaps();
    }

    @Override
    public Observable<List<Order>> getOrder(Context context, int position) {
        return mOrderManager.getMaps(context, position, EntityContext.getInstance().getmCurrentUser().getUserId());
    }

    public Observable<List<Order>> loadDatas(Context context, int position) {
        return ApiWrapper.getInstance().getOrders(position).filter(orderResult -> orderResult == null ? Boolean.FALSE : Boolean.TRUE)
                .map(OrderListsResult::getOrderList).doOnNext(orders -> mOrderManager.upDate(position, orders, context));
    }

    @Override
    public Observable<List<Order>> detect(Context context, Order order, int position) {
        return ApiWrapper.getInstance().deleteOrder(order.getOrder_num()).
                map(OrderReuslt::getOrder).
                doOnNext(order13 -> OrderDao.update(order13, context))
                .doOnNext(order12 -> mOrderManager.resert())
                .flatMap(new Func1<Order, Observable<List<Order>>>() {
                    @Override
                    public Observable<List<Order>> call(Order order1) {
                        return mOrderManager.getMaps(context, position, EntityContext.getInstance().getmCurrentUser().getUserId());
                    }
                });
    }

}
