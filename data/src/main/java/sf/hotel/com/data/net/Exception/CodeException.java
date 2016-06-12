package sf.hotel.com.data.net.Exception;

import sf.hotel.com.data.R;

/**
 * Created by FMT on 2016/6/12:10:26
 * EMAILE 1105896230@qq.com.
 */
public enum CodeException {
    LOGIN_NAME_ERROR(Code.LOGINNAMEERROR, R.string.login_name_error),
    LOGIN_PW_NULL(Code.LOGIGPWNULL, R.string.login_pw_null), LOGIN_NAME_NULL(Code.LOGINNAMENULl,R.string.login_name_null);

    private int code;
    private int messageId;

    CodeException(int code, int messageId) {

        this.code = code;
        this.messageId = messageId;
    }

    public int getCode() {
        return code;
    }

    public int getMessageId() {
        return messageId;
    }
}
