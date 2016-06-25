package sf.hotel.com.data.entity.netresult;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FMT on 2016/6/5:14:04
 * <p>
 * email 1105896230@qq.com.
 */
public class HttpResult<T> {

    @SerializedName("message")
    private String message;

    @SerializedName("timeStamp")
    private float timestamp;

    @SerializedName("code")
    private int code;

    @SerializedName("res")
    private T res;

    public boolean isSuccess() {
        if (code == 100) return true;
        return false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }
}
