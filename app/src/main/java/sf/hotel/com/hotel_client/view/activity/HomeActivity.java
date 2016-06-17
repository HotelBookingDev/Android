package sf.hotel.com.hotel_client.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
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
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showLog("事件通信异常" + throwable.getMessage());
                    }
                });
        mCompositeSubscription.add(subscribe);
    }
}
