package sf.hotel.com.hotel_client.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.hotel.HotelsFragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/23.
 */
public class HomeContainer extends BaseFragment {


    public static HomeContainer newInstance() {

        Bundle args = new Bundle();
        HomeContainer fragment = new HomeContainer();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container_home, container, false);
        init( savedInstanceState);
        return view;
    }

    private void init(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fragment_container_home_frame, HotelsFragment.newInstance());
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            super.onBackPressedSupport();
        }
        return true;
    }
}
