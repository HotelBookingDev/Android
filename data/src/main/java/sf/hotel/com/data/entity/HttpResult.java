package sf.hotel.com.data.entity;

/**
 * Created by FMT on 2016/6/5:14:04
 * EMAILE 1105896230@qq.com.
 */
public class HttpResult<T> {
    private String message;
    private long timestamp;

    private int status;

//    public Res<T> getRes() {
//        return res;
//    }
//
//    public void setRes(Res<T> res) {
//        this.res = res;
//    }
//
//    private Res<T> res;

    private T data;

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
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
