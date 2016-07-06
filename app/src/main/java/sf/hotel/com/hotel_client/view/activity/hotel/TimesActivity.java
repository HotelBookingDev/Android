package sf.hotel.com.hotel_client.view.activity.hotel;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.fragment.TimesFragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/27.
 */
public class TimesActivity extends BaseActivity {

    @BindView(R.id.activity_times_back)
    HotelTitleView mHotelTitleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);
        ButterKnife.bind(this);
        init();
        mHotelTitleView.addLeftClick(v -> finish());
    }

    private void init() {
        loadRootFragment(R.id.activity_times_frame, TimesFragment.newInstance());
    }



}
