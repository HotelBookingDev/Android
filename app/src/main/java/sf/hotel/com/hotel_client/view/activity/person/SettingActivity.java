package sf.hotel.com.hotel_client.view.activity.person;

import android.os.Bundle;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.fragment.person.SettingFragment;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        loadRootFragment(R.id.fl_setting, SettingFragment.newInstance());
    }
}
