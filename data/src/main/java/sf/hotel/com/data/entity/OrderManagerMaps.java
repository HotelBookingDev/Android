package sf.hotel.com.data.entity;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import sf.hotel.com.data.datasource.OrderDao;
import sf.hotel.com.data.entity.netresult.person.OrderResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * Created by "林其望".
 * DATE: 2016:07:21:14:51
 * email:1105896230@qq.com
 */

public class OrderManagerMaps {
    public HashMap<Integer, List<Order>> map = new HashMap<>();

    public Observable<List<Order>> getMaps(Context context, int position, long userId) {
        List<Order> orders = map.get(position);
        if (orders == null || orders.size() == 0) {
            orders = OrderDao.getOrder(context, position, userId);
        }
        return Observable.just(orders);
    }

    public void upDate(int postion, List<Order> orders, Context context) {
        if (orders == null) return;
        //内存中的数据是不准确的
        resert();
        map.put(postion, orders);
        OrderDao.update(orders, context);
    }

    public void resert() {
        map.clear();
    }
}
