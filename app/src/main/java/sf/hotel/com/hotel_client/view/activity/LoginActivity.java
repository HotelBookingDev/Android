package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;

import me.yokeyword.fragmentation.SupportFragment;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.LoginFragment;
import sf.hotel.com.hotel_client.view.fragment.RegisterFragment;

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

//    @Override
//    protected SupportFragment getFragmentByKey(Class fragment) {
////        SupportFragment mFragment;
////
////        if (fragment == FragConstant.LOGIN) {
////            LoginFragment loginFragment = new LoginFragment();
////            loginFragment.setStackClickListener(this);
////            mFragment = loginFragment;
////        } else {
////            RegisterFragment registerFragment = new RegisterFragment();
////            registerFragment.setStackClickListener(this);
////            mFragment = registerFragment;
////        }
////        return mFragment;
//        return super.getFragmentByKey(fragment);
//    }


    @Override
    public void onFragmentBackPressed() {
        super.onBackPressed();
    }
}
