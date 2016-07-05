package sf.hotel.com.data.entity;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sf.hotel.com.data.datasource.OrderDao;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
//管理订单的各自的结合,不建议用map管理开销大，不容易拓展
public class OrderManager {
    @SerializedName("finished")
    private List<Order> mAlreadyOrders;
    @SerializedName("inprocess")
    private List<Order> mNotOrders;

    public void setmAlreadyOrders(List<Order> mAlreadyOrders) {
        this.mAlreadyOrders = mAlreadyOrders;
    }

    public void setmNotOrders(List<Order> mNotOrders) {
        this.mNotOrders = mNotOrders;
    }

    public List<Order> getOrders(Context context, int position, long userId) {
        List<Order> mList = null;
        if (position == Order.ALRADYORDER) {
            mList = initDatas(context, mAlreadyOrders, position, userId);
        } else if (position == Order.NOTORDER) {
            mList = initDatas(context, mNotOrders, position, userId);
        }
        return mList;
    }

    private List<Order> initDatas(Context context, List<Order> mlists, int position, long userId) {
        if (mlists == null) {
            mlists = OrderDao.getOrder(context, position, userId);
        }
        return mlists;
    }

    public void update(Context context, OrderManager orderManager, long userId) {
        this.mAlreadyOrders = orderManager.getOrders(context, Order.ALRADYORDER, userId);
        this.mNotOrders = orderManager.getOrders(context, Order.NOTORDER, userId);
    }

    public void saveDb(Context context, OrderManager orderManager, long userId) {
        OrderDao.update(orderManager.getOrders(context, Order.ALRADYORDER, userId), context);
        OrderDao.update(orderManager.getOrders(context, Order.NOTORDER, userId), context);
    }
}
