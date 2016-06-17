package sf.hotel.com.hotel_client.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;
import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HomeActivity extends BaseActivity {

    //    @BindView(R.id.home_tab)
    PagerBottomTabLayout mPagerBottomTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        ButterKnife.bind(this);
        //initBottom();
        init();
        onRxEvent();
    }

    //可能这会是主界面
    private void initBottom() {
        Controller build = mPagerBottomTabLayout.builder()
                .addTabItem(android.R.drawable.ic_menu_camera, "相机", Color.BLUE)
                .addTabItem(android.R.drawable.ic_menu_compass, "位置", Color.BLUE)
                .addTabItem(android.R.drawable.ic_menu_search, "搜索", Color.BLUE)
                .addTabItem(android.R.drawable.ic_menu_help, "个人", Color.BLUE)
                .setMode(TabLayoutMode.HIDE_TEXT)
                .build();
        build.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {

            }

            @Override
            public void onRepeatClick(int index, Object tag) {

            }
        });
    }

    protected void init() {
        loadRootFragment(R.id.home_fragment, getFragmentByKey(FragConstant.HOTELS));
    }

    @Override
    public void onFragmentBackPressed() {
        super.onBackPressed();
    }

    public void showBottomTab() {
        mPagerBottomTabLayout.setVisibility(View.VISIBLE);
        Animation toTop = AnimationUtils.loadAnimation(this, R.anim.to_top);
        mPagerBottomTabLayout.setAnimation(toTop);
        toTop.start();
    }

    public void hideBottomTab() {
        Animation fromTop = AnimationUtils.loadAnimation(this, R.anim.from_top);
        mPagerBottomTabLayout.setAnimation(fromTop);
        fromTop.start();
        mPagerBottomTabLayout.setVisibility(View.INVISIBLE);
    }

    //订阅事件
    public void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(HotelMessage.class)
                .subscribe(new Action1<HotelMessage>() {
                    @Override
                    public void call(HotelMessage hotelMessage) {
                        //处理类型
                        switch (hotelMessage.what) {
                            case HotelMessage.SHOW_DETAIL_FRAGMENT:
                                showFragment(FragConstant.DETAIL);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //异常处理
                    }
                });
        mCompositeSubscription.add(subscribe);
    }
}
