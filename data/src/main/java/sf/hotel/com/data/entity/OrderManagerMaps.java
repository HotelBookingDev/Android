package sf.hotel.com.data.entity;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import sf.hotel.com.data.datasource.OrderDao;

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
            map.put(position, orders);
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

    //    避免出现重复的数据在分页的时候
    public void addLists(int position, List<Order> orders) {
        List<Order> orders1 = map.get(position);
        if (orders1 != null) {
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
//                重写了equals的方法如果出现重复的数据看下equals或者相关的数据
                if (orders1.contains(order)) {
                    orders1.remove(i);
                    orders1.add(i, order);
                } else {
                    orders1.add(order);
                }
            }
            map.put(position, orders1);
        }
    }
}
