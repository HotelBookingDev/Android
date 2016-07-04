package sf.hotel.com.data.entity;

import android.content.Context;

import java.util.List;

import sf.hotel.com.data.datasource.OrderDao;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
//管理订单的各自的结合,不建议用map管理开销大，不容易拓展
public class OrderManager {
    List<Order> mAlreadyOrders;
    List<Order> mNotOrders;

    public List<Order> getOrders(Context context, int position) {
        List<Order> mList = null;
        if (position == Order.ALRADYORDER) {
            mList = initDatas(context, mAlreadyOrders, position);
        } else if (position == Order.NOTORDER) {
            mList = initDatas(context, mNotOrders, position);
        }
        return mList;
    }

    private List<Order> initDatas(Context context, List<Order> mlists, int position) {
        if (mlists == null) {
            mlists = OrderDao.getOrder(context, position);
        }
        return mlists;
    }
}
