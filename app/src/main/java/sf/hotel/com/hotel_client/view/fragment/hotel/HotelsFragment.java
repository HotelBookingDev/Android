package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.lhh.ptrrv.library.footer.loadmore.BaseLoadMoreView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscription;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.DensityUtils;
import sf.hotel.com.hotel_client.view.activity.hotel.RoomActivity;
import sf.hotel.com.hotel_client.view.adapter.BaseRecyclerAdapter;
import sf.hotel.com.hotel_client.view.adapter.HomePullViewAdapter;
import sf.hotel.com.hotel_client.view.adapter.OnItemClickListener;
import sf.hotel.com.hotel_client.view.custom.DividerItemDecoration;
import sf.hotel.com.hotel_client.view.custom.hotelheader.RentalsSunHeaderView;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IHotelsView;
import sf.hotel.com.hotel_client.view.presenter.hotel.IHotelPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HotelsFragment extends BaseFragment implements IHotelsView {

    @BindView(R.id.fragment_hotels_list)
    RecyclerView mPullView;

    @BindView(R.id.fragment_hotels_ptr_frame)
    PtrClassicFrameLayout mPtrFrame;


    SearchItem mSearchItem;

    public static HotelsFragment newInstance() {
        Bundle args = new Bundle();

        HotelsFragment fragment = new HotelsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    HomePullViewAdapter mPullAdapter;

    private IHotelPresenter mIHotelPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hotels, container, false);
        ButterKnife.bind(this, view);
        mIHotelPresenter = new IHotelPresenter(this);

        initCache();
        initPullView();
        initPtrFrame();
        onRxEvent();
        initHotelCache();
        return view;
    }

    private void initPtrFrame() {
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                upDateDate();
            }
        });

        final RentalsSunHeaderView header = new RentalsSunHeaderView(getContext());
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(getBottomContext(),15), 0, DensityUtils.dp2px(getBottomContext(), 10));
        header.setUp(mPtrFrame);


        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);

        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);

        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);
    }

    private void upDateDate() {
        mIHotelPresenter.callHotelsByCityId("1");
    }


    public void refreshComplete(){
        mPtrFrame.refreshComplete();
    }


    private void initCache() {
        mIHotelPresenter.loadSearchItem();
    }

    private void initHotelCache() {
        HotelResult hotelCache = mIHotelPresenter.getHotelCache();
        if (hotelCache != null) {
            mPullAdapter.setDatas(hotelCache.getHotels());
        }
        mIHotelPresenter.callHotelsByCityId("1");
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(HotelMessage.class)
                .subscribe(hotelMessage -> {
                    switch (hotelMessage.what) {
                        case HotelMessage.REFRESH_LIST_VIEW_HOTEL:
                            CityBean cityBean = (CityBean) hotelMessage.obj;
                            mSearchItem.cityBean.setCode(cityBean.getCode());
                            mSearchItem.cityBean.setName(cityBean.getName());
                            mIHotelPresenter.callHotelsByCityId("1");
                            break;
                    }
                }, throwable -> {

                });
        mCompositeSubscription.add(subscribe);
    }

    private void initPullView() {

        View view = LayoutInflater.from(getBottomContext()).inflate(R.layout.header_hotels, null);
        mPullView.setLayoutManager(new LinearLayoutManager(getBottomContext()));

        mPullView.addItemDecoration(new DividerItemDecoration(getBottomContext(),
                DividerItemDecoration.VERTICAL_LIST));
        mPullAdapter = new HomePullViewAdapter(getBottomContext());
        mPullAdapter.setHeaderView(view);

        mPullAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                HotelsBean itemByPos = (HotelsBean) data;
                showDetail(itemByPos.getId());
            }
        });
        mPullView.setAdapter(mPullAdapter);
    }

    public void showDetail(int id) {
        Intent intent = new Intent(getBottomContext(), RoomActivity.class);
        intent.putExtra("action", "hotels");
        Bundle bundle = new Bundle();
        bundle.putInt("room", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIHotelPresenter != null) {
            mIHotelPresenter.destroy();
            mIHotelPresenter = null;
        }
    }

    @Override
    public void setHotelAdapterList(HotelResult hotelResult) {
        mPullAdapter.setDatas(hotelResult.getHotels());
    }

    public SearchItem getSearchItem() {
        return mSearchItem;
    }

    public void setSearchItem(SearchItem mSearchItem) {
        this.mSearchItem = mSearchItem;
    }
}
