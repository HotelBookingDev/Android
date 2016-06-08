package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.LoginFragment;
import sf.hotel.com.hotel_client.view.fragment.RegisterFragment;

public class LoginActivity extends SupportActivity implements LoginFragment.ClickListener {
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(savedInstanceState);
    }

    protected void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            if (loginFragment == null) {
                loginFragment = new LoginFragment();
                loginFragment.setClickListener(this);
            }
            loadRootFragment(R.id.login_fragment, loginFragment);
        }
    }

    @Override
    public void register() {
        if (registerFragment == null) {
            registerFragment = new RegisterFragment();
        }
        start(registerFragment);
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
