package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;
import android.view.Window;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    protected void init() {
        loadRootFragment(R.id.home_fragment, getFragmentByKey(FragConstant.HOTELS));
    }

    @Override
    public void onFragmentBackPressed() {
        super.onBackPressed();
    }
}
