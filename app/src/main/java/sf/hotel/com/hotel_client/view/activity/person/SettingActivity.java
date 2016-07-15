package sf.hotel.com.hotel_client.view.activity.person;

import android.os.Bundle;

import rx.Subscription;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.event.Message;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.PersonMessage;
import sf.hotel.com.hotel_client.view.fragment.person.ChangePwdFragment;
import sf.hotel.com.hotel_client.view.fragment.person.SettingFragment;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        loadRootFragment(R.id.fl_setting, SettingFragment.newInstance());
        initRxEvent();
    }

    private void initRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(Message.class)
                .subscribe(mMessage -> {
                    if (mMessage instanceof PersonMessage) {
                        switch (mMessage.what) {
                            case PersonMessage.FORGORPW:
                                start(ChangePwdFragment.newInstance());
                                break;
                        }
                    }
                });
        addSubscription(subscribe);
    }
}
