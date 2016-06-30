package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.DialogBedAdapter;
import sf.hotel.com.hotel_client.view.adapter.RoomRecyclerPagerAdapter;
import sf.hotel.com.hotel_client.view.custom.HideTitle;
import sf.hotel.com.hotel_client.view.custom.NoScrollView;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IRoomView;
import sf.hotel.com.hotel_client.view.presenter.hotel.IRoomPresenter;

/**
 * author MZ
 * email sanfenruxi1@163.com
 * date 16/6/16.
 */
public class RoomFragment extends BaseFragment implements IRoomView {

    private static Bundle args;

    IRoomPresenter mIRoomPresenter;

//    @BindView(R.id.fragment_room_close)
//    ImageView imgClose;

    @BindView(R.id.fragment_room_viewPager)
    RecyclerViewPager mRecyclerViewPager;

    RoomRecyclerPagerAdapter mRoomRecyclerPagerAdapter;

    @BindView(R.id.fragment_room_content)
    TextView mRoomContent;

    @BindView(R.id.frag_room_search)
    Button mBtnSearch;

    DialogPlus dialogPlus;

    @BindView(R.id.frag_room_title)
    HideTitle mTitle;

    @BindView(R.id.frag_room_scrollview)
    NoScrollView mNoScrollView;

    public static RoomFragment newInstance(Bundle bundle) {

        if (bundle != null) {
            args = bundle;
        } else {
            args = new Bundle();
        }
        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static RoomFragment newInstance() {

        Bundle args = new Bundle();
        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        mIRoomPresenter = new IRoomPresenter(this);
        ButterKnife.bind(this, view);
        initViewPager(args);

        // initRecyclerView();


        initTitle();


        return view;
    }

    private void initTitle() {
        mTitle.setScrollView(mNoScrollView);
        mTitle.addLeftViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

//    private void initRecyclerView() {
//        mPullAdapter = new DetailPullViewAdapter(getBottomContext());
//        mPullAdapter.setCount(5);
//        RoomLayoutManager layout = new RoomLayoutManager(getBottomContext());
//        mRecyclerView.setLayoutManager(layout);
//        mRecyclerView.setAdapter(mPullAdapter);
//    }

    private void initViewPager(Bundle bundle) {
        LinearLayoutManager layout = new LinearLayoutManager(getBottomContext(),
                LinearLayoutManager.HORIZONTAL, false);
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
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f /
                                    v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                    }
                }
            }
        });
        mRecyclerViewPager.addOnLayoutChangeListener(
                (v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
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
                });
        HotelResult.HotelsBean hotelsBean = bundle.getParcelable("room");
        if (hotelsBean != null) {
            List<HotelResult.HotelsBean.HotelLogoImgsBean> hotelLogoImgs = hotelsBean.getHotelLogoImgs();
            mRoomRecyclerPagerAdapter.setList(hotelLogoImgs);
            mRoomContent.setText(hotelsBean.getIntroduce());
        }
    }

    @OnClick(R.id.frag_room_search)
    public void onSearchClick() {

        if (dialogPlus == null) {
            dialogPlus = DialogPlus.newDialog(getBottomContext())
                    .setContentHolder(new ListHolder())
                    .setCancelable(true)
                    .setGravity(Gravity.BOTTOM)
                    .setFooter(R.layout.footer_bed)
                    .setHeader(R.layout.header_bed)
                    .setAdapter(new DialogBedAdapter(new ArrayList<>(), getBottomContext()))
                    .setOnItemClickListener((dialog, item, view, position) -> {

                    })
                    .setOnDismissListener(dialog -> {

                    })
                    .setOnCancelListener(dialog -> {

                    })
                    .setExpanded(true)
                    .create();
        }

        dialogPlus.show();
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void showViewToast(String msg) {

    }
}
