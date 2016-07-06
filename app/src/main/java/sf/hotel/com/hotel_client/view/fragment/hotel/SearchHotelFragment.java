package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/6.
 */
public class SearchHotelFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_search_hotle, container, false);

        ButterKnife.bind(this, view);

        return view;
    }



}
