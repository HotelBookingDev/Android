package sf.hotel.com.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FMT on 2016/6/5:14:04
 * EMAILE 1105896230@qq.com.
 */
public class HttpResult<T> {
    private String message;
    private long timestamp;

    private int status;

    public Resoult<T> getResoult() {
        return res;
    }

    public void setRes(Resoult<T> res) {
        this.res = res;
    }

    @SerializedName("Res")
    private Resoult<T> res;

    public boolean isSuccess() {
        if (status == 100) return true;
        return false;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return res.getData();
    }

    public void setData(T data) {
        this.res.setData(data);
    }
}
