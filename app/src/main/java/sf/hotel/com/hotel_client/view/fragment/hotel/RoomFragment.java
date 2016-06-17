package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.RoomRecyclerPagerAdapter;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;
import sf.hotel.com.hotel_client.view.event.hotel.MessageFactory;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IRoomView;
import sf.hotel.com.hotel_client.view.presenter.hotel.IRoomPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/16.
 */
public class RoomFragment extends BaseFragment implements IRoomView{

    IRoomPresenter mIRoomPresenter;

    @BindView(R.id.fragment_room_close)
    ImageView imgClose;

    @BindView(R.id.fragment_room_viewPager)
    RecyclerViewPager mRecyclerViewPager;

    RoomRecyclerPagerAdapter mRoomRecyclerPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        mIRoomPresenter = new IRoomPresenter(this);
        ButterKnife.bind(this, view);
        initViewPager();
        return view;
    }

    private void initViewPager() {

        LinearLayoutManager layout = new LinearLayoutManager(getBottomContext(), LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewPager.setLayoutManager(layout);

        mRoomRecyclerPagerAdapter = new RoomRecyclerPagerAdapter(getBottomContext());
        mRecyclerViewPager.setAdapter(mRoomRecyclerPagerAdapter);

    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void showViewToast(String msg) {

    }

    @OnClick(R.id.fragment_room_close)
    public void close(){
        RxBus.getDefault().post(MessageFactory.createHotelMessage(HotelMessage.FRAGMENT_BACK));
    }

    @Override
    public void success(int type) {

    }

    @Override
    public void failed(int type, Throwable e) {

    }
}
