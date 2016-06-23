package sf.hotel.com.hotel_client.view.activity.person;

import android.os.Bundle;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.fragment.person.OrderFragment;
import sf.hotel.com.hotel_client.view.fragment.person.UserInfoFragment;

public class UserInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        loadRootFragment(R.id.fl_user_info, UserInfoFragment.newInstance());
    }
}
