package sf.hotel.com.hotel_client.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.LruCache;
import android.view.KeyEvent;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.LoginFragment;
import sf.hotel.com.hotel_client.view.fragment.RegisterFragment;

public class LoginActivity extends BaseActivity implements LoginFragment.ClickListener {

    private final int LOGIN = 0x1;
    private final int REGISTER = 0x2;

    private LruCache<Integer, Fragment> mFragmentList = new LruCache<>(3);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init() {
        showFragment(LOGIN);
    }

    @Override
    public void register() {
        showFragment(LOGIN, REGISTER);
    }

//    private void changeFragment(int type) {
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.login_fragment, getFragment(type));
//        fragmentTransaction.commit();
//    }

    private void showFragment(int from) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment, getFragment(from));
        fragmentTransaction.commit();
    }

    private void showFragment(int from, int to) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment, getFragment(to));
        //动画效果 。。。
        fragmentTransaction.hide(getFragment(from));
        fragmentTransaction.commit();
    }

    protected Fragment getFragment(int type) {
        Fragment fragment = mFragmentList.get(type);
        if (fragment == null) {
            switch (type) {
                case LOGIN:
                    fragment = new LoginFragment();
                    LoginFragment loginFragment = (sf.hotel.com.hotel_client.view.fragment.LoginFragment) fragment;
                    loginFragment.setClickListener(this);
                    break;
                case REGISTER:
                    fragment = new RegisterFragment();
                    break;
            }
            mFragmentList.put(type, fragment);
        }
        return fragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isReturn = true;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isReturn = super.onKeyDown(keyCode, event);
        }
        return isReturn;
    }
}
