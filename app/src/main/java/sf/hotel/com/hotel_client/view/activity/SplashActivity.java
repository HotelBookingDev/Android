package sf.hotel.com.hotel_client.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avos.avoscloud.PushService;

import sf.hotel.com.hotel_client.utils.HotelImageLoad;
import sf.hotel.com.hotel_client.view.activity.register.LoginActivity;
import sf.hotel.com.hotel_client.view.interfaceview.login.ISplashView;
import sf.hotel.com.hotel_client.view.presenter.login.ISplashPresenter;

/**
 * @author MZ
 *         email sanfenruxi1@163.com
 *         date 16/6/12.
 */
public class SplashActivity extends BaseActivity implements ISplashView {
    ISplashPresenter mPreseneter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        在这里初始化Activity
        PushService.setDefaultPushCallback(this, PushActivity.class);
        mPreseneter = new ISplashPresenter(this);
        initView();
        initDatas();
    }

    private void initView() {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        HotelImageLoad.loadImage(this, imageView, getImage());
        setContentView(imageView);
    }

    private void initDatas() {
        mPreseneter.initDatas();
    }

    public void startLoginActivity() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }, 3000);
    }

    @Override
    public void showImage(Object obj) {

    }

    public Object getImage() {
        return mPreseneter.getImage();
    }

    //    必须要调这个方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPreseneter.destroy();
    }
}
