package sf.hotel.com.hotel_client.view.activity.hotel;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.fragment.hotel.BookingFragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public class BookingActivity extends BaseActivity {

    @BindView(R.id.activity_booking_title)
    HotelTitleView mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        onRxEvent();

        init();

        mTitle.addLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void init() {
        loadRootFragment(R.id.activity_booking_frame, BookingFragment.newInstance());
    }


    private void onRxEvent() {
    }

}
