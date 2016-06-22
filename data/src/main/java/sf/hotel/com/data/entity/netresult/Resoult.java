package sf.hotel.com.data.entity.netresult;

/**
 * Created by FMT on 2016/6/12:17:14
 * EMAILE 1105896230@qq.com.
 */
public class Resoult<T> {
    T data;

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Res{" +
                "data=" + data +
                '}';
    }

    public T getData() {
        return data;
    }
}
