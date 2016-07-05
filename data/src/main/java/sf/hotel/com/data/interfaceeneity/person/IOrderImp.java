package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.entity.OrderManager;
import sf.hotel.com.data.entity.netresult.person.OrderManagerResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class IOrderImp implements IOrder {
    OrderManager mOrderManager;

    public IOrderImp() {
        mOrderManager = new OrderManager();
    }

    @Override
    public Observable<List<Order>> getOrderByDb(Context context, int position) {
        return Observable.just(mOrderManager.getOrders(context, position,
                EntityContext.getInstance().getmCurrentUser().getUserId()));
    }

    public Observable<List<Order>> getOrderByNet(Context context, int position) {
        return ApiWrapper.getInstance()
                .getOrderManager()
                .filter(orderManagerResult -> orderManagerResult ==
                        null ? Boolean.FALSE : Boolean.TRUE)
                .map(OrderManagerResult::getManager)
                .doOnNext(orderManager -> mOrderManager.saveDb(context, orderManager,
                        EntityContext.getInstance().getmCurrentUser().getUserId()))
                .doOnNext(orderManager -> mOrderManager.update(context, orderManager,
                        EntityContext.getInstance().getmCurrentUser().getUserId()))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(orderManager -> mOrderManager.getOrders(context, position,
                        EntityContext.getInstance().getmCurrentUser().getUserId()));
    }
}
