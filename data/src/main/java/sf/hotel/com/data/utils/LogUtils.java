package sf.hotel.com.data.utils;

import com.orhanobut.logger.Logger;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public final class LogUtils {
    private static boolean DEBUG = true;
    private static final String TAG = "Hotel";

    public static void v(String log) {
        v(TAG, log);
    }

    static {
        Logger.init(TAG).methodCount(1).hideThreadInfo();
    }

    public static void v(String tag, String log) {
        if (DEBUG) {
            Logger.t(tag).v(log);
        }
    }

    public static void d(String log) {
        d(TAG, log);
    }

    public static void d(String tag, String log) {
        if (DEBUG) {
            Logger.t(tag).d(log);
        }
    }

    public static void w(String log) {
        w(TAG, log);
    }

    public static void w(String tag, String log) {
        if (DEBUG) {
            Logger.t(tag).w(log);
        }
    }

    public static void i(String log) {
        i(TAG, log);
    }

    public static void i(String tag, String log) {
        if (DEBUG) {
            Logger.t(tag).i(log);
        }
    }

    public static void e(String log) {
        e(TAG, log);
    }

    public static void e(String tag, String log) {
        if (DEBUG) {
            Logger.t(tag).e(log);
        }
    }

    public static void printExceptionStackTrace(Exception e) {
        if (e != null && DEBUG) {
            Logger.e(e, e.getMessage());
        }
    }

    public static void logJson(String json) {
        Logger.t(TAG).json(json);
    }

    public static void logThrowadle(Throwable throwable) {
        if (throwable == null) return;
        Logger.e(throwable, throwable.getMessage());
    }
}
