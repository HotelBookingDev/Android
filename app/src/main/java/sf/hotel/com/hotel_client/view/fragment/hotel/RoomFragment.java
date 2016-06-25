package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.RoomRecyclerPagerAdapter;
import sf.hotel.com.hotel_client.view.custom.CircleIndicator;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;
import sf.hotel.com.hotel_client.view.event.hotel.MessageFactory;
import sf.hotel.com.hotel_client.view.event.hotel.RoomMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.fragment.person.PersonFragment;
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
    LoopRecyclerViewPager mRecyclerViewPager;

    RoomRecyclerPagerAdapter mRoomRecyclerPagerAdapter;


    @BindView(R.id.fragment_room_content)
    TextView mRoomContent;


    @BindView(R.id.fragment_room_pager_indicator)
    CircleIndicator circleIndicator;

    public static RoomFragment newInstance(Bundle bundle){
        Bundle args;
        if (bundle !=null){
            args = bundle;
        }else {
            args = new Bundle();
        }
        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater,   container,   savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        mIRoomPresenter = new IRoomPresenter(this);
        ButterKnife.bind(this, view);


        if (savedInstanceState != null){
            initViewPager(savedInstanceState);
        }
        return view;
    }

    private void initViewPager(Bundle bundle) {

        LinearLayoutManager layout = new LinearLayoutManager(getBottomContext(), LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewPager.setLayoutManager(layout);
        mRecyclerViewPager.setTriggerOffset(0.15f);
        mRecyclerViewPager.setFlingFactor(0.25f);


        mRoomRecyclerPagerAdapter = new RoomRecyclerPagerAdapter(getBottomContext());
        mRecyclerViewPager.setAdapter(mRoomRecyclerPagerAdapter);

        mRecyclerViewPager.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
//                mPositionText.setText("First: " + mRecyclerViewPager.getFirstVisiblePosition());
                int childCount = mRecyclerViewPager.getChildCount();
                int width = mRecyclerViewPager.getChildAt(0).getWidth();
                int padding = (mRecyclerViewPager.getWidth() - width) / 2;

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                    }
                }
            }
        });

        mRecyclerViewPager.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mRecyclerViewPager.getChildCount() < 3) {
                    if (mRecyclerViewPager.getChildAt(1) != null) {
                        View v1 = mRecyclerViewPager.getChildAt(1);
                        v1.setScaleY(0.9f);
                    }
                } else {
                    if (mRecyclerViewPager.getChildAt(0) != null) {
                        View v0 = mRecyclerViewPager.getChildAt(0);
                        v0.setScaleY(0.9f);
                    }
                    if (mRecyclerViewPager.getChildAt(2) != null) {
                        View v2 = mRecyclerViewPager.getChildAt(2);
                        v2.setScaleY(0.9f);
                    }
                }

            }
        });
        HotelResult.HotelsBean hotelsBean = (HotelResult.HotelsBean) bundle.getSerializable("room");

        if (hotelsBean!=null){
            List<HotelResult.HotelsBean.HotelLogoImgsBean> hotelLogoImgs = hotelsBean.getHotelLogoImgs();
            mRoomContent.setText(hotelsBean.getIntroduce());
        }

        circleIndicator.setViewPager(mRecyclerViewPager);
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
