package sf.hotel.com.data.net.Exception;

import sf.hotel.com.data.R;

/**
 * Created by FMT on 2016/6/12:10:26
 * EMAILE 1105896230@qq.com.
 */
public enum CodeException {
    LOGIN_FORMAT_ERROR(Code.LOGIN_FORMAT_ERROR, R.string.login_name_error),
    LOGIN_PWD_NULL(Code.LOGIN_PWD_NULL, R.string.login_pw_null),

    LOGIN_PWD_ERROR(Code.LOGIN_PWD_ERROR, R.string.login_pw_error),
    LOGIN_NAME_NULL(Code.LOGIN_NAME_NULL, R.string.login_name_null),
    HAS_BEEN_REGISTER(Code.HAS_BEEN_REGISTER, R.string.has_been_register),

    REGIST_PHONE_NULL(Code.REGIST_PHONE_NULL, R.string.regist_phone_null),

    REGIST_PHONE_ERROR(Code.REGIST_PHONE_ERROR, R.string.regist_phone_error),
    SMS_CODE_NULL(Code.SMS_CODE_NULL, R.string.sms_code_null),

    PWD_NULL(Code.PW_NULL, R.string.reigst_pw_null);

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
