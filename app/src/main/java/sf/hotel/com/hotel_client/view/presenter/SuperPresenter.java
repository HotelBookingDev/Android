package sf.hotel.com.hotel_client.view.presenter;

import android.content.Context;

/**
 * Created by FMT on 2016/6/12:10:49
 * EMAILE 1105896230@qq.com.
 */
public class SuperPresenter implements Presenter {
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void handlingException(Throwable e) {

    }

    String getErrorString(int id, Context context) {
        return context.getString(id);
    }
}
