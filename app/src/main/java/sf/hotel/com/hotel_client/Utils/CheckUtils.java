package sf.hotel.com.hotel_client.Utils;

import android.text.TextUtils;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by FMT on 2016/6/3:19:34
 * EMAILE 1105896230@qq.com.
 */

public final class CheckUtils {
    private CheckUtils() {

    }

    public static boolean checkPhoneTextView(TextView tvUsername) {
        if (CheckUtils.isTextViewEmpty(tvUsername)) {
            return false;
        }
        if (!CheckUtils.checkPhoneNumber(tvUsername.getText().toString())) {
            return false;
        }
        return true;
    }

    public static boolean isTextViewEmpty(TextView tv) {
        return tv == null ||
                tv.getText() == null ||
                TextUtils.isEmpty(tv.getText().toString().trim());
    }

    /**
     * 检测手机号码是否符合格式
     *
     * @param number 手机号码
     * @return true 手机号码符合规范 flase 手机号不符合规范
     */
    private static boolean checkPhoneNumber(String number) {
        try {
            Pattern p = Pattern.compile("^1\\d{10}$");
            Matcher m = p.matcher(number);
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
