package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HomeActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init(savedInstanceState);
    }

    protected void init(Bundle savedInstanceState) {
        loadRootFragment(R.id.home_fragment, getFragmentByKey(FragConstant.HOTELS));
    }

//    @Override
//    protected SupportFragment getFragmentByKey(Class fragment) {
////        SupportFragment mFragment = null;
////        if (fragment == HomeActivity.HOTELS){
////
////            HotelsFragment hotelsFragment = new HotelsFragment();
////            mFragment = hotelsFragment;
////
////            }
////        return mFragment;
//        return super.getFragmentByKey(fragment);
//    }


    @Override
    public void onFragmentBackPressed() {
        super.onBackPressed();
    }
}
