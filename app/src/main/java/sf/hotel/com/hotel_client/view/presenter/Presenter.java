package sf.hotel.com.hotel_client.view.presenter;

/**
 * Created by FMT on 2016/6/3:18:54
 * EMAILE 1105896230@qq.com.
 */
public interface Presenter {
    void resume();

    void pause();

    void destroy();

    void handlingException(Throwable e);

}
