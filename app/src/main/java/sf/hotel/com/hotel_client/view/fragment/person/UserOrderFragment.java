package sf.hotel.com.hotel_client.view.fragment.person;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.UserOrderAdapter;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.OrderMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IUserOrderView;
import sf.hotel.com.hotel_client.view.presenter.person.UserOrderPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserOrderFragment extends BaseFragment implements IUserOrderView {

    private UserOrderPresenter mUserOrderPresenter;

    public static UserOrderFragment newInstance() {

        Bundle args = new Bundle();

        UserOrderFragment fragment = new UserOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.rv_order)
    RecyclerView mRecyclerview;

    private UserOrderAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userd_order, container, false);
        ButterKnife.bind(this, view);
        initRxevent();
        initRecycle();
        mUserOrderPresenter = new UserOrderPresenter(this);
        mUserOrderPresenter.getDatas();
        return view;
    }

    private void initRecycle() {
        LinearLayoutManager layout = new LinearLayoutManager(getBottomContext(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(layout);
    }

    private void initRxevent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(OrderMessage.class)
                .subscribe(orderMessage -> {
                    if (orderMessage == null) return;
                    showViewToast(orderMessage.what + "");
                });
        mCompositeSubscription.add(subscribe);
    }

    @Override
    public void showOrder(List<Order> mOrders) {
        if (mOrders == null) return;
        if (mAdapter == null) {
            mAdapter = new UserOrderAdapter(getActivity(), mOrders);
        } else {
            mAdapter.setOrders(mOrders);
            mAdapter.notifyDataSetChanged();
        }
        mRecyclerview.setAdapter(mAdapter);
    }
}
