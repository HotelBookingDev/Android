package sf.hotel.com.hotel_client.view.fragment.person.orders;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsedOrder extends BaseFragment {
    public static UsedOrder newInstance() {

        Bundle args = new Bundle();

        UsedOrder fragment = new UsedOrder();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_userd_order, container, false);
    }
}
