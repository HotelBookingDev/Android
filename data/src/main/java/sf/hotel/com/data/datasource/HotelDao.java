package sf.hotel.com.data.datasource;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import sf.hotel.com.data.entity.netresult.HotelResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/28.
 */
public class HotelDao{

    public static void add(HotelResult hotel, Context context) {
        try {
            DatabaseHelper.getHelper(context).getHotelDao().createIfNotExists(hotel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(HotelResult hotel, Context context) {
        try {
            DatabaseHelper.getHelper(context).getHotelDao().createOrUpdate(hotel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isCache(long id, Context context) {
        boolean isCache = false;
        HotelResult hotelResult = getHotelResult(id, context);
        if (hotelResult != null) {
            isCache = true;
        }
        return isCache;
    }

    public static HotelResult getHotelResult(long hotelId, Context context){
        HotelResult hotelResult = null;
        try {
            List<HotelResult> hotelResultList = DatabaseHelper.getHelper(context)
                    .getHotelDao()
                    .queryForEq("id", hotelId);

            if (hotelResultList != null && hotelResultList.size() == 1){
                hotelResult = hotelResultList.get(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotelResult;
    }
}
