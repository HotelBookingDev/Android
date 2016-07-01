package sf.hotel.com.data.datasource;

import android.content.Context;

import com.google.gson.Gson;

import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * @author MZ
 * email sanfenruxi1@163.com
 * data 16/6/28.
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

    public static ProvincesResult.ProvincesBean.CityBean getCitysBean(Context context){
        Gson gson = new Gson();
        String citysBean = PreferencesUtils.getCitysBean(context);
        return gson.fromJson(citysBean, ProvincesResult.ProvincesBean.CityBean.class);
    }

    public static void saveCitysBean(Context context, ProvincesResult.ProvincesBean.CityBean cityBean){
        Gson gson = new Gson();
        String citysJson = gson.toJson(cityBean);
        PreferencesUtils.saveCitysBean(context, citysJson);
    }


    public static void saveProcincesResult(Context context, ProvincesResult provincesResult){
        Gson gson = new Gson();
        String s = gson.toJson(provincesResult);
        PreferencesUtils.saveProcincesResult(context, s);
    }

    public static ProvincesResult getProcincesResult(Context context){
        Gson gson = new Gson();
        String procincesResult = PreferencesUtils.getProcincesResult(context);
        return gson.fromJson(procincesResult, ProvincesResult.class);
    }
}
