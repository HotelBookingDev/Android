package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;

import sf.hotel.com.hotel_client.R;

public class LoginActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init(savedInstanceState);
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
