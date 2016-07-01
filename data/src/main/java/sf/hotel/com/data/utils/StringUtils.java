package sf.hotel.com.data.utils;

import android.text.TextUtils;
import android.util.Log;

import java.security.MessageDigest;

/**
 * Created by FMT on 2016/6/13:17:07
 * EMAILE 1105896230@qq.com.
 */
public class StringUtils {
    private static final String TAG = StringUtils.class.getSimpleName();
    private static final char HEX_DIGITS[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    //    以后密码要在这里改
    public static String changePud(String s) {

        return s;
    }

    public static String md5(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes("utf-8"));
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            Log.e("StringUtils", "changePud error", e);
            return "";
        }
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }
}
