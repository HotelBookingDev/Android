package sf.hotel.com.hotel_client.view.activity.person;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.fragment.person.OrderFragment;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.view_title)
    HotelTitleView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        loadRootFragment(R.id.order_content, OrderFragment.newInstance());
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleView.addLeftClick(v -> finish());
    }
}
