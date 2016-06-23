package sf.hotel.com.hotel_client.view.activity.person;

import android.os.Bundle;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.fragment.person.OrderFragment;

public class OrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        loadRootFragment(R.id.fl_order, OrderFragment.newInstance());
    }
}
