package sf.hotel.com.data.datasource;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import rx.Observable;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.entity.OrderAndHotel;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class OrderDao {
    public static void addOrder(OrderAndHotel order, Context context) {
        initdata(order);
        try {
            DatabaseHelper.getHelper(context).getOrders().createIfNotExists(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(OrderAndHotel order, Context context) {
        initdata(order);
        try {
            DatabaseHelper.getHelper(context).getOrders().createOrUpdate(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(List<OrderAndHotel> list, Context context) {
        if (list == null) return;
        for (OrderAndHotel order : list) {
            update(order, context);
        }
    }

    public static List<OrderAndHotel> getOrder(Context context, int position, long userId) {
        List<OrderAndHotel> mLists = null;
        if (position == Order.ALRADYORDER || position == Order.NOTORDER) {
            try {
                QueryBuilder<OrderAndHotel, Integer> orderIntegerQueryBuilder = DatabaseHelper.getHelper(
                        context).getOrders().queryBuilder();
                orderIntegerQueryBuilder.where().eq("state", position);
                orderIntegerQueryBuilder.where().eq("user_id", userId);
                orderIntegerQueryBuilder.orderBy("time", false);
                mLists = orderIntegerQueryBuilder.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mLists;
    }

    private static void initdata(OrderAndHotel mOrderHotel) {
        Observable.just(mOrderHotel)
                .filter(orderAndHotel -> mOrderHotel == null ? Boolean.FALSE : Boolean.TRUE)
                .filter(orderAndHotel -> mOrderHotel.getmHotelshot() ==
                        null ? Boolean.FALSE : Boolean.TRUE)
                .filter(orderAndHotel -> mOrderHotel.getmOrder() ==
                        null ? Boolean.FALSE : Boolean.TRUE)
                .subscribe(orderAndHotel -> {
                    mOrderHotel.getmHotelshot().setId(mOrderHotel.getmOrder().getOrder_num());
                    mOrderHotel.setNum(mOrderHotel.getmOrder().getOrder_num());
                    mOrderHotel.setState(mOrderHotel.getmOrder().getState());
                    mOrderHotel.setTime(mOrderHotel.getmOrder().getTime());
                    mOrderHotel.setUser_id(mOrderHotel.getmOrder().getId());
                });
    }
}
