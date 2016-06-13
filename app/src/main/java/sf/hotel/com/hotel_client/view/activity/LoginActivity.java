package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.LruCache;

import me.yokeyword.fragmentation.SupportFragment;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.ClickListener;
import sf.hotel.com.hotel_client.view.fragment.LoginFragment;
import sf.hotel.com.hotel_client.view.fragment.RegisterFragment;

public class LoginActivity extends BaseActivity implements ClickListener {

    public static Class LOGIN = LoginFragment.class;
    public static Class REGISTER = RegisterFragment.class;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init(savedInstanceState);
    }

    protected void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.login_fragment, getFragmentByKey(LoginActivity.LOGIN));
        }
    }

    @Override
    protected SupportFragment getFragmentByKey(Class fragment) {
        SupportFragment mFragment;

        if (fragment == LOGIN) {
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.setClickListener(this);
            mFragment = loginFragment;
        } else {
            RegisterFragment registerFragment = new RegisterFragment();
            registerFragment.setClickListener(this);
            mFragment = registerFragment;
        }
        return mFragment;
    }


    @Override
    public void showFragmentByClass(Class fragment) {
        super.showFragment(fragment);
    }

    @Override
    public void startActivityByClass(Class clazz) {
        super.startActivity(clazz);
    }

    @Override
    public void onFragmentBackPressed() {
        super.onBackPressed();
    }
}
