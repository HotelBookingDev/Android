package sf.hotel.com.hotel_client.view.activity.hotel;

import android.os.Bundle;

import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.fragment.HomeContainer;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/6.
 */
public class HotelsActivity extends BaseActivity{




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        loadRootFragment(R.id.activity_hotel_frame, HomeContainer.newInstance());
    }

}
