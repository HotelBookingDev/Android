package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;

import rx.Subscription;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.LoginMessage;

public class LoginActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init(savedInstanceState);
        onRxEvent();
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
                            showFragment(FragConstant.REGISTER);
                            break;
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    protected void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.login_fragment, getFragmentByKey(FragConstant.LOGIN));
        }
    }

    @Override
    public void onFragmentBackPressed() {
        super.onBackPressed();
    }
}
