package sf.hotel.com.hotel_client.view.fragment.person;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

public class CampaignInfoFragment extends BaseFragment {


    public static CampaignInfoFragment newInstance() {

        Bundle args = new Bundle();

        CampaignInfoFragment fragment = new CampaignInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.view_title)
    HotelTitleView titleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_campaign_info, container, false);
        ButterKnife.bind(this, view);
        initviews();
        return view;
    }

    private void initviews() {
        titleView.addLeftClick(v -> getActivity().finish());
    }
}
