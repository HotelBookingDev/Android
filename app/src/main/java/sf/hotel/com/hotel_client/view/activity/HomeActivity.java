package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_tab)
    PagerBottomTabLayout mPagerBottomTabLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init(savedInstanceState);
        initBottom();
    }


    //可能这会是主界面
    private void initBottom(){
        mPagerBottomTabLayout.builder()
                .addTabItem(android.R.drawable.ic_menu_camera, "相机")
                .addTabItem(android.R.drawable.ic_menu_compass, "位置")
                .addTabItem(android.R.drawable.ic_menu_search, "搜索")
                .addTabItem(android.R.drawable.ic_menu_help, "帮助")
                .build();
    }


    protected void init(Bundle savedInstanceState) {
        loadRootFragment(R.id.home_fragment, getFragmentByKey(FragConstant.HOTELS));
    }


    @Override
    public void onFragmentBackPressed() {
        super.onBackPressed();
    }
}
