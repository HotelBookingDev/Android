package sf.hotel.com.hotel_client.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.IRegisterPwFragment;
import sf.hotel.com.hotel_client.view.fragment.LoginFragment;

public class LoginActivity extends BaseActivity implements LoginFragment.ClickLinstener {

    private final int LOGING = 0x1;
    private final int REGISTER = 0x2;
    private LoginFragment mLoginFragment;
    private IRegisterPwFragment mIRegisterPwFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init() {
        changeFragment(LOGING);
    }

    @Override
    public void regiser() {
        changeFragment(REGISTER);
    }

    private void changeFragment(int type) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment, getFragment(type));
        fragmentTransaction.commit();
    }

    protected Fragment getFragment(int type) {
        Fragment fragment = null;
        if (type == LOGING) {
            if (mLoginFragment == null) {
                mLoginFragment = new LoginFragment();
            }
            fragment = mLoginFragment;
            ((LoginFragment) fragment).setmClickLinstener(this);
        } else if (type == REGISTER) {
            if (mIRegisterPwFragment == null) {
                mIRegisterPwFragment = new IRegisterPwFragment();
            }
            fragment = mIRegisterPwFragment;
        }
        return fragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isReturn = true;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        } else {
            return isReturn = super.onKeyDown(keyCode, event);
        }
        return isReturn;
    }
}
