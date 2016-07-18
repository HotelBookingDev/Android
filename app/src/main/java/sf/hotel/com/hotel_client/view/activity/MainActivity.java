package sf.hotel.com.hotel_client.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.register.LoginActivity;
import sf.hotel.com.hotel_client.view.custom.PayBottomView;
import sf.hotel.com.hotel_client.view.event.Message;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.fragment.HomeContainer;
import sf.hotel.com.hotel_client.view.fragment.hotel.SearchHotelFragment;
import sf.hotel.com.hotel_client.view.fragment.person.PersonGroupFragment;

public class MainActivity extends BaseActivity {
    @BindView(R.id.home_tab)
    PagerBottomTabLayout mPagerBottomTabLayout;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private SupportFragment[] mFragments = new SupportFragment[4];

    //当前
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragmets();
        initBottom();
        initView();
        RxEvent();
    }

    private void initFragmets() {
        mFragments[FIRST] = SearchHotelFragment.newInstance();
        mFragments[SECOND] = PersonGroupFragment.newInstance();
    }

    private void RxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(Message.class)
                .subscribe(mMessage -> {
                    if (mMessage instanceof LoginMessage) {
                        switch (mMessage.what) {
                            case LoginMessage.SHOW_LOGIN:
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.putExtra("isFinish", true);
                                startActivity(intent);
                                break;
                        }
                    }
                });
        addSubscription(subscribe);
    }

    private void initView() {
        loadMultipleRootFragment(R.id.fl_main_content, FIRST, mFragments[FIRST],
                mFragments[SECOND]);
    }

    //可能这会是主界面
    private void initBottom() {
        Controller build = mPagerBottomTabLayout.builder()
                .addTabItem(android.R.drawable.ic_menu_search, "首页", Color.BLUE)
                .addTabItem(android.R.drawable.ic_menu_help, "个人", Color.BLUE)
                .setMode(TabLayoutMode.HIDE_TEXT)
                .build();
        build.addTabItemClickListener(new TabItemListene());
    }

    private class TabItemListene implements OnTabItemSelectListener {

        @Override
        public void onSelected(int index, Object tag) {
            showHideFragment(mFragments[index], mFragments[currentIndex]);
            currentIndex = index;

        }

        //列表也可以回到自定页或者刷新数据
        @Override
        public void onRepeatClick(int index, Object tag) {

        }
    }
}
