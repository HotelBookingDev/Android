package sf.hotel.com.hotel_client.view.activity.hotel;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;
import rx.Subscription;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.activity.FragConstant;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HomeActivity extends BaseActivity {

    @BindView(R.id.activity_home_tab)
    PagerBottomTabLayout mPagerBottomTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        init();
        initBottom();
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
                .setMessageBackgroundColor(0x33aaaaaa)
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

    private void init() {
        loadRootFragment(R.id.home_fragment, getFragmentByKey(FragConstant.HOTELS));
    }

    private void showBottomTab() {
        if (mPagerBottomTabLayout.getVisibility() == View.GONE) {
            mPagerBottomTabLayout.setVisibility(View.VISIBLE);
            Animation toTop = AnimationUtils.loadAnimation(this, R.anim.to_top);
            mPagerBottomTabLayout.setAnimation(toTop);
            toTop.start();
        }
    }

    private void hideBottomTab() {
        if (mPagerBottomTabLayout.getVisibility() == View.VISIBLE) {
            Animation fromTop = AnimationUtils.loadAnimation(this, R.anim.from_top);
            mPagerBottomTabLayout.setAnimation(fromTop);
            fromTop.start();
            mPagerBottomTabLayout.setVisibility(View.GONE);
        }
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(HotelMessage.class)
                .subscribe(hotelMessage -> {
                    //处理类型
                    switch (hotelMessage.what) {
                        case HotelMessage.SHOW_DETAIL_FRAGMENT:
                            showFragment(FragConstant.DETAIL);
                            break;
                        case HotelMessage.SHOW_ROOM_FRAGMENT:
                            showFragment(FragConstant.ROOM);
                            break;
                        case HotelMessage.SHOW_BOTTOM_VIEW:
                            showBottomTab();
                            break;
                        case HotelMessage.HIDE_BOTTOM_VIEW:
                            hideBottomTab();
                            break;
                        case HotelMessage.FRAGMENT_BACK:
                            onBackPressed();
                    }
                }, throwable -> {
                    showLog("事件通信异常" + throwable.getMessage());
                });
        mCompositeSubscription.add(subscribe);
    }
}
