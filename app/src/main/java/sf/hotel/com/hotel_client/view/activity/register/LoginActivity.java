package sf.hotel.com.hotel_client.view.activity.register;

import android.os.Bundle;

import rx.Subscription;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.activity.MainActivity;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.fragment.login.FillInfolationFragment;
import sf.hotel.com.hotel_client.view.fragment.login.LoginFragment;
import sf.hotel.com.hotel_client.view.fragment.login.RegisterFragment;

public class LoginActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init(savedInstanceState);
        onRxEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(LoginMessage.class)
                .subscribe(loginMessage -> {
                    switch (loginMessage.what) {
                        case LoginMessage.SHOW_MAIN:
                            if (getIntent() == null ||
                                    getIntent().getBooleanExtra("isFinish", false)) {
                                finish();
                            } else {
                                startActivity(MainActivity.class);
                            }
                            break;
                        case LoginMessage.SHOW_REGIST:
                            start(RegisterFragment.newInstance());
                            break;
                    }
                });
        addSubscription(subscribe);
    }

    private void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.login_fragment, LoginFragment.newInstance());
        }
    }
}
