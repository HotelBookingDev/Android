package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;
import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.hotel.HotelsActivity;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/6.
 */
public class SearchHotelFragment extends BaseFragment {


    @BindView(R.id.search_hotel)
    FancyButton search_hotel;

    public static SearchHotelFragment newInstance() {

        Bundle args = new Bundle();
        SearchHotelFragment fragment = new SearchHotelFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_search_hotle, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick({R.id.search_hotel})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.search_hotel:


                showHotel();

                break;
        }

    }

    private void showHotel() {
        Intent intent = new Intent(getBottomContext(), HotelsActivity.class);

        startActivity(intent);
    }


}
