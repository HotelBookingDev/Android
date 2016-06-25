package sf.hotel.com.hotel_client.view.fragment.person;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.OrderPagerFragmentAdapter;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

public class OrderFragment extends BaseFragment {

    public static OrderFragment newInstance() {

        Bundle args = new Bundle();

        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.tl_order)
    TabLayout mTabLayout;
    @BindView(R.id.vp_order)
    ViewPager mViewPager;

    @BindView(R.id.view_title)
    HotelTitleView mTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mViewPager.setAdapter(new OrderPagerFragmentAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

        mTitle.addLeftonClick(v -> getActivity().finish());
    }
}
