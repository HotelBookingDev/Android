package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.util.LruCache;

import me.yokeyword.fragmentation.SupportFragment;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.HotelsFragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HomeActivity extends BaseActivity{
    public final static int HOTELS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init(savedInstanceState);
    }

    protected void init(Bundle savedInstanceState) {
        loadRootFragment(R.id.home_fragment, (SupportFragment) getFragmentByKey(HOTELS));
    }

    @Override
    protected Fragment getFragmentByKey(int fragment) {
        Fragment mFragment = mFragmentList.get(fragment);

        if (mFragment == null){
            switch (fragment){
                case HOTELS:
                    HotelsFragment hotelsFragment = new HotelsFragment();
                    mFragment = hotelsFragment;
                    mFragmentList.put(HOTELS, hotelsFragment);
                    break;
            }
            mFragmentList.put(fragment, mFragment);
        }
        return mFragment;
    }
}
