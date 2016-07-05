package sf.hotel.com.data.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import sf.hotel.com.data.entity.LocalOrder;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.entity.OrderAndHotel;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.entity.netresult.HotelResult;

/**
 * Created by FMT on 2016/6/3:16:39
 * EMAILE 1105896230@qq.com.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME = "hotel.db";
    private static final int databaseVersion = 14;
    private Dao<UserEntity, Integer> userDao;
    private Dao<HotelResult, Integer> hotelDao;
    private Dao<LocalOrder, Integer> localOrders;
    private Dao<OrderAndHotel, Integer> orders;
    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, databaseVersion);
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserEntity.class);
//            TableUtils.createTable(connectionSource, HotelResult.class);
            TableUtils.createTable(connectionSource, LocalOrder.class);
            TableUtils.createTable(connectionSource, OrderAndHotel.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
            int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, UserEntity.class, true);
//            TableUtils.dropTable(connectionSource, HotelResult.class, true);
            TableUtils.dropTable(connectionSource, LocalOrder.class, true);
            TableUtils.dropTable(connectionSource, OrderAndHotel.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<UserEntity, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(UserEntity.class);
        }
        return userDao;
    }

    public Dao<HotelResult, Integer> getHotelDao() throws SQLException {
        if (hotelDao == null) {
            hotelDao = getDao(HotelResult.class);
        }
        return hotelDao;
    }

    public Dao<LocalOrder, Integer> getLocalOrders() throws SQLException {
        if (localOrders == null) {
            localOrders = getDao(LocalOrder.class);
        }
        return localOrders;
    }

    public Dao<OrderAndHotel, Integer> getOrders() throws SQLException {
        if (orders == null) {
            orders = getDao(OrderAndHotel.class);
        }
        return orders;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        userDao = null;
        hotelDao = null;
        localOrders = null;
        orders = null;
    }
}
