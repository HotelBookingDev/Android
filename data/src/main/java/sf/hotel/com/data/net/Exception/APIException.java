package sf.hotel.com.data.net.Exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public class APIException extends Exception{
    public int mCode;

    private static Map<Integer, String> CODE_MAP = new HashMap<>();

    private static final int E_FAIL = -1;
    private static final int S_OK = 1;

    static {
        CODE_MAP.put(E_FAIL, "请求失败");
    }

    public APIException(){

    }

    public static String getErrorMessage(APIException e) {
        if (e == null) {
            return "空异常";
        }

        if (CODE_MAP.containsKey(e.mCode)) {
            return CODE_MAP.get(e.mCode);
        } else {
            return "错误" + e.mCode + e.getMessage();
        }
    }

    public static String getErrorMessage(int code) {
        if (CODE_MAP.containsKey(code)) {
            return CODE_MAP.get(code);
        } else {
            return "未知错误" + code;
        }
    }

    public APIException(String message) {
        super(message);
    }

    public APIException(int code) {
        super();

        mCode = code;
    }

    public APIException(Throwable cause) {
        super(cause);
    }

    public APIException(int code, String message) {
        super(message);
        mCode = code;
    }

}
