package sf.hotel.com.hotel_client.view.activity;

import android.graphics.Color;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;
import sf.hotel.com.hotel_client.R;

public class MainActivity extends BaseActivity {
    @BindView(R.id.home_tab)
    PagerBottomTabLayout mPagerBottomTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottom();
        initView();
    }

    private void initView() {
        loadRootFragment(R.id.fl_main_content, getFragmentByKey(FragConstant.HOTELS));
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
        build.addTabItemClickListener(new TabItemListene());
    }

    @Override
    public void onFragmentBackPressed() {
        super.onBackPressed();
    }

    class TabItemListene implements OnTabItemSelectListener {

        @Override
        public void onSelected(int index, Object tag) {
            if (index == 3) {
                start(getFragmentByKey(FragConstant.PERSON));
            }
        }

        //列表也可以回到自定页或者刷新数据
        @Override
        public void onRepeatClick(int index, Object tag) {

        }
    }
}
