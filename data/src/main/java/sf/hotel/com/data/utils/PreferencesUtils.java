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

    public static void saveInstallationId(Context context, String session) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        preferences.edit().putString(INSTALLATIONID, session).apply();
    }

    public static String getInstallationId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(HOTEL_PREF, 0);
        return preferences.getString(INSTALLATIONID, null);
    }
}
