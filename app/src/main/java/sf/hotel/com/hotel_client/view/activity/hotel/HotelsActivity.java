package sf.hotel.com.hotel_client.view.activity.hotel;

import android.os.Bundle;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;
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

        onRxEvent();
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault().toObservable(HotelMessage.class).subscribe(new Action1<HotelMessage>() {
            @Override
            public void call(HotelMessage hotelMessage) {
                switch (hotelMessage.what){
                    case HotelMessage.ACTIVITY_FINISH:
                        finish();
                        break;
                }
            }
        });
       addSubscription(subscribe);
    }

    private void init() {
        loadRootFragment(R.id.activity_hotel_frame, HomeContainer.newInstance());
    }

}
