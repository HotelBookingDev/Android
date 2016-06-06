package sf.hotel.com.data.utils;

import android.util.Log;

/**
 * Created by FMT on 2016/6/6:09:53
 * EMAILE 1105896230@qq.com.
 */
public final class LLog {
    private static final boolean CLOSE = false;

    public static void e(String tag, String msg) {
        if (!CLOSE) {
            Log.e(tag, msg);
        }
    }
}
