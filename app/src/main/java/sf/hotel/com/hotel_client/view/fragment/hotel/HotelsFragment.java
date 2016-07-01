package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.lhh.ptrrv.library.footer.loadmore.BaseLoadMoreView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.hotel.RoomActivity;
import sf.hotel.com.hotel_client.view.adapter.HomePullViewAdapter;
import sf.hotel.com.hotel_client.view.adapter.OnItemClickListener;
import sf.hotel.com.hotel_client.view.custom.DividerItemDecoration;
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
    PullToRefreshRecyclerView mPullView;

    static ProvincesResult.ProcincesBean.CityBean mCityBean = new ProvincesResult.ProcincesBean.CityBean();

    //TODO 不要用static 已经有了SharePerenni 的东西内部自己去获取
    public static HotelsFragment newInstance(ProvincesResult.ProcincesBean.CityBean cityBean) {
        mCityBean = cityBean;
        Bundle args = new Bundle();

        HotelsFragment fragment = new HotelsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static HotelsFragment newInstance() {

        Bundle args = new Bundle();
        HotelsFragment fragment = new HotelsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    HomePullViewAdapter mPullAdapter;

    private IHotelPresenter mIHotelPresenter;
    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hotels, container, false);
        ButterKnife.bind(this, view);
        mIHotelPresenter = new IHotelPresenter(this);
        initPullView();
        onRxEvent();
        initHotelCache();
        return view;
    }

    private void initHotelCache() {
        HotelResult hotelCache = mIHotelPresenter.getHotelCache();
        if (hotelCache != null) {
            mPullAdapter.setList(hotelCache.getHotels());
        }
        mIHotelPresenter.callHotelsByCityId(String.valueOf(mCityBean.getId()), "1");
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(HotelMessage.class)
                .subscribe(new Action1<HotelMessage>() {
                    @Override
                    public void call(HotelMessage hotelMessage) {
                        switch (hotelMessage.what) {
                            case HotelMessage.REFRESH_LIST_VIEW_HOTEL:
                                ProvincesResult.ProcincesBean.CityBean cityBean = (ProvincesResult.ProcincesBean.CityBean) hotelMessage.obj;
                                mCityBean.setId(cityBean.getId());
                                mCityBean.setName(cityBean.getName());
                                mIHotelPresenter.callHotelsByCityId(
                                        String.valueOf(mCityBean.getId()), "1");
                                break;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    private void initPullView() {
        mPullView.setSwipeEnable(true);
        //加载更多
        mPullView.setLayoutManager(new LinearLayoutManager(getBottomContext()));
        mPullView.setPagingableListener(new PullToRefreshRecyclerView.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
                mIHotelPresenter.loadMoreHotel();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPullAdapter.setCount(mPullAdapter.getItemCount() + 20);
                        mPullAdapter.notifyDataSetChanged();
                        mPullView.onFinishLoading(true, false);
                        showLog("show");
                    }
                });
            }
        });

        //设置间隔线
        mPullView.getRecyclerView()
                .addItemDecoration(new DividerItemDecoration(getBottomContext(),
                        DividerItemDecoration.VERTICAL_LIST));

        //设置上拉加载
        BaseLoadMoreView loadMoreView = new BaseLoadMoreView(getBottomContext(),
                mPullView.getRecyclerView());
        mPullView.setLoadMoreFooter(loadMoreView);

        //刷新
        mPullView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mIHotelPresenter.callHotelsByCityId(String.valueOf(mCityBean.getId()), "1");
                        mPullView.setOnRefreshComplete();
                        mPullView.onFinishLoading(true, false);
                    }
                });
            }
        });
        //设置适配器
        mPullAdapter = new HomePullViewAdapter(getBottomContext());
        mPullAdapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                HotelResult.HotelsBean itemByPos = mPullAdapter.getItemByPos(position);

                showDetail(itemByPos);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mPullView.setAdapter(mPullAdapter);
        mPullView.onFinishLoading(true, false);
    }

    public void showDetail(HotelResult.HotelsBean hotelsBean) {
        Intent intent = new Intent(getBottomContext(), RoomActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("room", hotelsBean);
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
        mPullAdapter.setList(hotelResult.getHotels());
    }
}
