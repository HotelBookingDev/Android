package sf.hotel.com.hotel_client.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by 林其望
 * email: 1105896230@qq.com
 */
public class AndroidUtils {
    /**
     * 因为判断是否为小米系统是有一个读文件的过程，所以不每次都读取判断，只第一次才判断
     */
    public static boolean mIsJudgementBefore = false;
    public static boolean mIsMIUIOS = false;

    /**
     * 判断是否是MIUI系统
     *
     * @return true 是小米平台
     */
    public static boolean isMIUI() {
        try {
            if (!mIsJudgementBefore) {
                mIsJudgementBefore = true;
                Properties properties = new Properties();
                properties.load(new FileInputStream(
                        new File(Environment.getRootDirectory(), "build.prop")));
                mIsMIUIOS = properties.getProperty("ro.miui.ui.version.code", null) != null ||
                        properties.getProperty("ro.miui.ui.version.name", null) != null ||
                        properties.getProperty("ro.miui.internal.storage", null) != null;
            }
        } catch (Exception e) {
        }
        return mIsMIUIOS;
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //判断当前语言版本是否是中文
    public static boolean isZh(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh")) { return true; } else return false;
    }
}
