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
            if (position == Order.ALRADYORDER) {
                mlists = OrderDao.getOrder(context, true, userId);
            } else if (position == Order.NOTORDER) {
                mlists = OrderDao.getOrder(context, false, userId);
            }
        }
        return mlists;
    }

    //    将网络请求后的集合更新到内存中
    public void update(Context context, OrderManager orderManager, long userId) {
        this.mAlreadyOrders = orderManager.getOrders(context, Order.ALRADYORDER, userId);
        this.mNotOrders = orderManager.getOrders(context, Order.NOTORDER, userId);
    }

    //    这个时候的orderManger是网络请求返回的ordermanage 里面的集合是最新的集合
    public void saveDb(Context context, OrderManager orderManager, long userId) {
        OrderDao.update(orderManager.getOrders(context, Order.ALRADYORDER, userId), context);
        OrderDao.update(orderManager.getOrders(context, Order.NOTORDER, userId), context);
    }
}
