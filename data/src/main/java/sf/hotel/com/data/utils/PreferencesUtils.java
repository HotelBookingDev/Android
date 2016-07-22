package sf.hotel.com.data.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by FMT on 2016/6/13:11:01
 * EMAILE 1105896230@qq.com.
 */
public class PreferencesUtils {
    public static final String HOTEL_PREF = "hotel_pref";

    public static final String INSTALLATIONID = "installationid";

    public static final String PHONE = "phone";

    public static final String PASSWORD = "password";

    public static final String USERID = "userId";
    //用户头像
    public static final String AVATAR = "avatar";
    //token
    public static final String TOKEN = "token";

    //是否接受消息
    public static final String ACCPETMSG = "accpetmesg";

    //当前城市和百度地图对于的citycode
    public static final String CITYCODE = "city_code";

    public static final String CITY_NAME = "city_name";

    //当前是否是登录状态，在登录界面初始化设置为未登录的状态
    public static final String ISLOGIN = "is_login";

    public static final String HOTEL_RESULT = "hotel_result";

    public static final String HOTEL_CITYS = "hotel_citys";

    public static final String HOTEL_PROC = "hotel_proc";

    public static final String HOTEL_SEARCH = "hotel_search";

    public static final String HOTEL_ROOM_BOOKING = "hotel_room_booking";

    private static void put(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putString(key, value).apply();
    }

    private static String get(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(key, null);
    }

    public static void saveInstallationId(Context context, String installationId) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putString(INSTALLATIONID, installationId).apply();
    }

    public static String getInstallationId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(INSTALLATIONID, null);
    }

    public static void savePhone(Context context, String phone) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putString(PHONE, phone).apply();
    }

    public static String getPhone(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(PHONE, null);
    }

    public static void savePassWord(Context context, String pwd) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putString(PASSWORD, pwd).apply();
    }

    public static String getPassWord(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(PASSWORD, null);
    }

    public static void saveAvatar(Context context, String url) {
        if (!TextUtils.isEmpty(url)) {
            SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
            preferences.edit().putString(AVATAR, url).apply();
        }
    }

    public static String getAvatar(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(AVATAR, null);
    }

    public static void saveToken(Context context, String token) {
        if (!TextUtils.isEmpty(token)) {
            SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
            preferences.edit().putString(TOKEN, token).apply();
        }
    }

    public static String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(TOKEN, null);
    }

    public static void saveCityCode(Context context, String code) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putString(CITYCODE, code).apply();
    }

    public static void saveCityName(Context context, String name) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putString(CITY_NAME, name).apply();
    }

    public static String getCityName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(CITY_NAME, null);
    }

    public static String getCityCode(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(CITYCODE, null);
    }

    public static void saveAcceptMeg(Context context, boolean isAccept) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putBoolean(ACCPETMSG, isAccept).apply();
    }

    public static boolean getAcceptMeg(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getBoolean(ACCPETMSG, true);
    }

    //在Login和ApplicationonCreate中设置为false，由于项目初期可能开始界面不一样在Application中设置false
    public static void saveLogin(Context context, boolean isLogin) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putBoolean(ISLOGIN, isLogin).apply();
    }

    public static boolean getLogin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getBoolean(ISLOGIN, false);
    }

    public static void saveUserId(Context context, String userId) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putString(USERID, userId).apply();
    }

    public static String getUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(USERID, "");
    }

    public static void saveHotelResult(Context context, String hotelJson) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putString(HOTEL_RESULT, hotelJson).apply();
    }

    public static String getHotelResult(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(HOTEL_RESULT, "");
    }

    public static String getCitysBean(Context context) {
        return get(context, HOTEL_CITYS);
    }

    public static void saveCitysBean(Context context, String citysBean) {
        put(context, HOTEL_CITYS, citysBean);
    }

    public static String getProcincesResult(Context context) {return get(context, HOTEL_PROC);}

    public static void saveProcincesResult(Context context, String procincesResult) {
        put(context, HOTEL_PROC, procincesResult);
    }

    public static String getSearchItem(Context context){
        return get(context, HOTEL_SEARCH);
    }
    public static void saveSearchItem(Context context,String str){
        put(context, HOTEL_SEARCH, str);
    }

    public static String getBookingBean(Context context){
        return get(context, HOTEL_ROOM_BOOKING);
    }
    public static void saveBookingBean(Context context,String str){
        put(context, HOTEL_ROOM_BOOKING, str);
    }
}
