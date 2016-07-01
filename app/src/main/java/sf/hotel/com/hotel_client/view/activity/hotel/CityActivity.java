package sf.hotel.com.hotel_client.view.activity.hotel;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.CityMessage;
import sf.hotel.com.hotel_client.view.fragment.hotel.CityFragment;

/**
 * author MZ
 * email sanfenruxi1@163.com
 * date 16/6/23.
 */
//TODO 只是包了一个CityFragment没有过多的处理
public class CityActivity extends BaseActivity {

    @BindView(R.id.activity_city_back)
    HotelTitleView mHotelTitleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);
        init();
        onRxEvent();

        mHotelTitleView.addLeftClick(v -> finish());
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(CityMessage.class)
                .subscribe(cityMessage -> {
                }, throwable -> {
                });
        mCompositeSubscription.add(subscribe);
    }

    private void init() {
        loadRootFragment(R.id.activity_city_frame, CityFragment.newInstance());
    }
}
