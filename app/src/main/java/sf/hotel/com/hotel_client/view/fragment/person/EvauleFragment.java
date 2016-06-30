package sf.hotel.com.hotel_client.view.fragment.person;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

public class EvauleFragment extends BaseFragment {
    public static EvauleFragment newInstance() {

        Bundle args = new Bundle();

        EvauleFragment fragment = new EvauleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.view_title)
    HotelTitleView view_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ evaule, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        view_title.addLeftClick(v -> getActivity().finish());
    }
}
