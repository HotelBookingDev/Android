package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.lhh.ptrrv.library.footer.loadmore.BaseLoadMoreView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.data.entity.ProcincesResult;
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

    static ProcincesResult.ProcincesBean.CitysBean mCitysBean = new ProcincesResult.ProcincesBean.CitysBean();


    public static HotelsFragment newInstance(ProcincesResult.ProcincesBean.CitysBean citysBean) {

        mCitysBean = citysBean;

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
        if (hotelCache != null){
            mPullAdapter.setList(hotelCache.getHotels());
        }
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(HotelMessage.class)
                .subscribe(new Action1<HotelMessage>() {
                    @Override
                    public void call(HotelMessage hotelMessage) {
                        switch (hotelMessage.what){
                            case HotelMessage.SUCCESS:
                                HotelResult hotelResult = (HotelResult) hotelMessage.obj;
                                mPullAdapter.setList(hotelResult.getHotels());
                                break;
                            case HotelMessage.REFRESH_LIST_VIEW_HOTEL:
                                ProcincesResult.ProcincesBean.CitysBean citysBean = (ProcincesResult.ProcincesBean.CitysBean) hotelMessage.obj;
                                mCitysBean.setId(citysBean.getId());
                                mCitysBean.setName(citysBean.getName());
                                mIHotelPresenter.callHotelsByCityId(String.valueOf(mCitysBean.getId()), "1");
                                break;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showViewToast(throwable.getMessage() + "加载失败");
                        showLog(throwable.getMessage());
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
                        mIHotelPresenter.callHotelsByCityId("1", "1");
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
            public void onItemClick(View view,
                                    int position) {

                HotelResult.HotelsBean itemByPos = mPullAdapter.getItemByPos(position);

                showDetail(itemByPos);

            }

            @Override
            public void onItemLongClick(View view,
                                        int position) {

            }
        });
        mPullView.setAdapter(mPullAdapter);
        mPullView.onFinishLoading(true, false);
    }

    public void showDetail(HotelResult.HotelsBean hotelsBean) {
        Intent intent = new Intent(getBottomContext(), RoomActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("room", hotelsBean);
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
        if (mIHotelPresenter != null){
            mIHotelPresenter.destroy();
            mIHotelPresenter = null;
        }

    }
}
