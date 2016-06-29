package sf.hotel.com.data.datasource;

import android.content.Context;

import com.google.gson.Gson;

import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/28.
 */
public class HotelDao{

    public static void saveHotelResult(Context context, HotelResult hotelResult){
        Gson gson = new Gson();
        String hotelJson = gson.toJson(hotelResult);
        PreferencesUtils.saveHotelResult(context, hotelJson);
    }

    public static HotelResult getHotelResult(Context context){
        Gson gson = new Gson();
        String hotelJson = PreferencesUtils.getHotelResult(context);
        return gson.fromJson(hotelJson, HotelResult.class);
    }
}
