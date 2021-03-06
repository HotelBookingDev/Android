package sf.hotel.com.hotel_client.view.activity.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.CityMessage;
import sf.hotel.com.hotel_client.view.fragment.HomeContainer;
import sf.hotel.com.hotel_client.view.fragment.hotel.CityFragment;

/**
 * author MZ
 * email sanfenruxi1@163.com
 * date 16/6/23.
 */
public class CityActivity extends BaseActivity {

    @BindView(R.id.activity_city_back)
    HotelTitleView mHotelTitleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);

        onRxEvent();

        init();
        mHotelTitleView.addLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(CityMessage.class)
                .subscribe(cityMessage -> {
                        switch (cityMessage.what){
                            case CityMessage.ACTIVITY_FINISH:
                                finish();
                                break;
                        }
                }, throwable -> {
                });
        addSubscription(subscribe);
    }

    private void init() {
        loadRootFragment(R.id.activity_city_frame, CityFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(HomeContainer.CITY_REQUEST_CODE, intent);
        finish();
    }
}
