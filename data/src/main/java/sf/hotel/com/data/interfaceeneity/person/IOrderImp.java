package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import java.util.List;

import rx.Observable;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.entity.OrderManager;

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
        return Observable.just(mOrderManager.getOrders(context, position));
    }

    public Observable<List<Order>> getOrderByNet(Context context, int position) {
        return null;
    }
}
