package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.transulcent.TransulcentUtils;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;

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

        mHotelTitleView.addLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TransulcentUtils.setFixWindow(this);
    }

    private void init() {
        loadRootFragment(R.id.activity_times_frame, getFragmentByKey(FragConstant.Times));
    }

}