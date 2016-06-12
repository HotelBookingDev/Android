package sf.hotel.com.data.net.Exception;

import android.content.Context;

import sf.hotel.com.data.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public class APIException extends Exception {
    private int mCode;
    private String mMessage;
    private int mMessageId;

    public APIException(int code, String message) {
        super(message);
        mCode = code;
        mMessage = message;
    }


    public APIException(CodeException mCodeException) {
        this.mCode = mCodeException.getCode();
        this.mMessageId = mCodeException.getMessageId();
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

    public int getCode() {
        return mCode;
    }

    public int getMessageId() {
        return mMessageId;
    }


    public String getErrorMessage(Context context) {
        String error = context.getResources().getString(R.string.error);
        for (CodeException exception : CodeException.values()) {
            if (exception.getCode() == mCode) {
                error = context.getResources().getString(exception.getMessageId());
            }
        }
        return error;
    }
}
