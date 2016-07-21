package sf.hotel.com.hotel_client.view.fragment.person;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.OrderMessage;
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
    @BindView(R.id.view_title)
    HotelTitleView mView_title;
    private String[] mTab = new String[]{"待确认", "未入住", "已完成"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTabLayout.addTab(mTabLayout.newTab().setText(mTab[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTab[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTab[2]));
        mTabLayout.setSelectedTabIndicatorColor(getBottomContext().getResources().getColor(R.color.default_button));
        mView_title.addLeftClick(v -> getActivity().finish());
        mTabLayout.setOnTabSelectedListener(new TabSelectListener());
        loadRootFragment(R.id.fl_order, UserOrderFragment.newInstance());
    }

    //用户选择的tab的监听
    private class TabSelectListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            RxBus.getDefault().post(getMessage(tab.getPosition()));
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    private OrderMessage getMessage(int type) {
        return MessageFactory.createOrderMessage(UITypeToOrderType(type));
    }

    private int UITypeToOrderType(int type) {
        int message = OrderMessage.PENDING_CONFIRMATION;
        if (type == 0) {
            message = OrderMessage.PENDING_CONFIRMATION;
        } else if (type == 1) {
            message = OrderMessage.NOTCONSUMED;
        } else if (type == 2) {
            message = OrderMessage.ALREADYCONSUMED;
        }
        return message;
    }
}
