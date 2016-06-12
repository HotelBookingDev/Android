package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.LruCache;
import android.view.KeyEvent;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.ClickListener;
import sf.hotel.com.hotel_client.view.fragment.LoginFragment;
import sf.hotel.com.hotel_client.view.fragment.RegisterFragment;

public class LoginActivity extends SupportActivity implements ClickListener {




    LruCache<Integer, Fragment> mFragmentList = new LruCache<>(3);
    public final static int LOGIN = 1;
    public final static int REGISTER = 2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init(savedInstanceState);
    }

    protected void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.login_fragment, (SupportFragment) getFragmentByKey(LOGIN));
        }
    }

    @Override
    public void showFragment(int fragment){
        start((SupportFragment) getFragmentByKey(fragment));
    }

    public Fragment getFragmentByKey(int fragment){
        Fragment mFragment = mFragmentList.get(fragment);

        if (mFragment == null){
            switch (fragment){

                case LOGIN:
                    LoginFragment loginFragment = new LoginFragment();
                    loginFragment.setClickListener(this);
                    mFragment = loginFragment;
                    break;

                case REGISTER:

                    RegisterFragment registerFragment = new RegisterFragment();
                    registerFragment.setClickListener(this);
                    mFragment = registerFragment;
                    break;
            }

        }
        return mFragment;
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
