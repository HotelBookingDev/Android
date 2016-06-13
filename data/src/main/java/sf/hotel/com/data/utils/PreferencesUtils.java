package sf.hotel.com.data.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by FMT on 2016/6/13:11:01
 * EMAILE 1105896230@qq.com.
 */
public class PreferencesUtils {
    public static final String HOTEL_PREF = "hotel_pref";

    public static final String INSTALLATIONID = "installationid";

    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";

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
}
