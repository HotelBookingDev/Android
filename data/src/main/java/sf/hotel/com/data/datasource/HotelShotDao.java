package sf.hotel.com.data.datasource;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import sf.hotel.com.data.entity.Hotelshot;

/**
 * Created by 林其望
 * data：2016/7/13
 * email: 1105896230@qq.com
 */
public class HotelShotDao {

    public static void update(Hotelshot hotelshot, Context context) {
        if (hotelshot == null) return;
        try {
            DatabaseHelper.getHelper(context).getHotelShot().createOrUpdate(hotelshot);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Hotelshot hotelshot, Context context) {
        try {
            DatabaseHelper.getHelper(context).getHotelShot().delete(hotelshot);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Hotelshot getHotelshot(long number, Context context) {
        Hotelshot Hotelshot = null;
        try {
            List<sf.hotel.com.data.entity.Hotelshot> hotelshots = DatabaseHelper.getHelper(context)
                    .getHotelShot()
                    .queryForEq("id", number);
            if (hotelshots != null && hotelshots.size() > 0) {
                Hotelshot = hotelshots.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Hotelshot;
    }
}
