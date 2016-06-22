package sf.hotel.com.hotel_client.view.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.HotelImageLoad;

/**
 * Created by FMT on 2016/6/12:10:49
 * EMAILE 1105896230@qq.com.
 */
public abstract class SuperPresenter implements Presenter {
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void handlingException(Throwable e) {

    }

    //加载头像
    protected void initAvater(Context context, ImageView imageView) {
        String avatr = PreferencesUtils.getAvatar(context);
        if (TextUtils.isEmpty(avatr)) {
            HotelImageLoad.loadImageCircle(context, imageView, R.mipmap.head_loading_bj);
        } else {
            HotelImageLoad.loadImageCircle(context, imageView, avatr);
        }
    }
}
