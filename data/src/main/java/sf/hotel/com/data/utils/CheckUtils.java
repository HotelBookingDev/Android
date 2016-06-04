package sf.hotel.com.data.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by FMT on 2016/6/4:17:40
 * EMAILE 1105896230@qq.com.
 */
public class CheckUtils {
    public static boolean checkPhoneNumber(String number) {
        try {
            Pattern p = Pattern.compile("^1\\d{10}$");
            Matcher m = p.matcher(number);
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        String strPatten = "^[a-zA-Z0-9][\\w\\.-]*@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPatten);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isTextViewEmpty(String str) {
        return str == null || str.equals("");
    }
}
