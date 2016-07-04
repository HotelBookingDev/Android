package sf.hotel.com.data.datasource;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import sf.hotel.com.data.entity.LocalOrder;
import sf.hotel.com.data.utils.TimeUtils;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
//查询订单会用到
public class LocalOrderDao {

    public static void add(LocalOrder order, Context context) {
        try {
            order.setFirstsearch_time(TimeUtils.getCurrentTimes());
            DatabaseHelper.getHelper(context).getLocalOrders().createIfNotExists(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(LocalOrder order, Context context) {
        try {
            LocalOrder localOrder = getLocalOrder(order.getOrderNum(), context);
            if (localOrder != null) {
                order.setFirstsearch_time(localOrder.getFirstsearch_time());
            }
            DatabaseHelper.getHelper(context).getLocalOrders().createOrUpdate(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isCache(String phoneNum, Context context) {
        boolean isCache = false;
        LocalOrder mLocalOrder = getLocalOrder(phoneNum, context);
        if (mLocalOrder != null) {
            isCache = true;
        }
        return isCache;
    }

    public static List<LocalOrder> getLocalOrders(long userId, Context context) {
        List<LocalOrder> mLocalOrders = null;
        try {
            QueryBuilder<LocalOrder, Integer> hotelResultIntegerQueryBuilder = DatabaseHelper.getHelper(
                    context).getLocalOrders().queryBuilder();
            //降序排序
            hotelResultIntegerQueryBuilder.orderBy("update_time", false);

            hotelResultIntegerQueryBuilder.where().eq("user_id", userId);
            mLocalOrders = hotelResultIntegerQueryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mLocalOrders;
    }

    public static LocalOrder getLocalOrder(String localId, Context context) {
        LocalOrder mLocalOrder = null;
        try {
            List<LocalOrder> mLocalOrders = DatabaseHelper.getHelper(context)
                    .getLocalOrders()
                    .queryForEq("order_id", localId);
            if (mLocalOrders != null && mLocalOrders.size() == 1) {
                mLocalOrder = mLocalOrders.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mLocalOrder;
    }
}
