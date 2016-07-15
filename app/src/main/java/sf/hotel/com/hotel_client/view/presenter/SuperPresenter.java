package sf.hotel.com.hotel_client.view.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.HotelImageLoad;

/**
 * Created by FMT on 2016/6/12:10:49
 * EMAILE 1105896230@qq.com.
 */
public abstract class SuperPresenter implements Presenter {
    private CompositeSubscription mCompositeSubscription;

    public SuperPresenter() {
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void handlingException(Throwable e) {
        if (e != null) {
            LogUtils.logThrowadle(e);
        }
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

    //当前用户是否登陆
    public boolean checkIsLogin() {
        return EntityContext.getInstance().getmCurrentUser() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    public String getErrorString(int id, Context context) {
        try {
            return context.getResources().getString(id);
        } catch (Exception e) {
            return context.getResources().getString(R.string.error);
        }
    }

    @Override
    public void destroy() {
        if (mCompositeSubscription != null) mCompositeSubscription.unsubscribe();
    }

    protected void addSubsrcicitpition(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
}
