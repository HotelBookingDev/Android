package sf.hotel.com.data.entity;

/**
 * Created by FMT on 2016/6/4:16:43
 * EMAILE 1105896230@qq.com.
 */
public class StateEntity<T> {
    int code = 200;
    T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    private void setData(T data) {
        throw new RuntimeException("can not set");
    }

    public StateEntity(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public StateEntity(T data) {
        this.data = data;
    }
}
