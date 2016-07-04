package sf.hotel.com.data.datasource;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import sf.hotel.com.data.entity.Order;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class OrderDao {
    public void addOrder(Order order, Context context) {
        try {
            DatabaseHelper.getHelper(context).getOrders().createIfNotExists(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Order order, Context context) {
        try {
            DatabaseHelper.getHelper(context).getOrders().createOrUpdate(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Order> getOrder(Context context, int position) {
        List<Order> mLists = null;
        if (position == Order.ALRADYORDER || position == Order.NOTORDER) {
            try {
                QueryBuilder<Order, Integer> orderIntegerQueryBuilder = DatabaseHelper.getHelper(
                        context).getOrders().queryBuilder();
                orderIntegerQueryBuilder.where().eq("state", position);
                orderIntegerQueryBuilder.orderBy("time", false);
                mLists = orderIntegerQueryBuilder.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mLists;
    }
}
