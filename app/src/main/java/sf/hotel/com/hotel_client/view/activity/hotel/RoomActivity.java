package sf.hotel.com.hotel_client.view.activity.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.RoomMessage;
import sf.hotel.com.hotel_client.view.fragment.hotel.RoomFragmentV2;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/24.
 */
public class RoomActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        onRxEvent();
        initIntent();
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(RoomMessage.class)
                .subscribe(new Action1<RoomMessage>() {
                    @Override
                    public void call(RoomMessage roomMessage) {
                        if (roomMessage != null) {
                            switch (roomMessage.what) {
                                case RoomMessage.ACTIVITY_BACK:
                                    finish();
                                    break;
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscription(subscribe);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                init(bundle);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void init(Bundle bundle) {
        loadRootFragment(R.id.activity_hotel_frame, RoomFragmentV2.newInstance(bundle));
    }
}
