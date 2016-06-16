package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
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
import sf.hotel.com.hotel_client.R;

import sf.hotel.com.hotel_client.view.activity.FragConstant;
import sf.hotel.com.hotel_client.view.adapter.DetailPullViewAdapter;
import sf.hotel.com.hotel_client.view.custom.DividerItemDecoration;

import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IDetailView;
import sf.hotel.com.hotel_client.view.presenter.hotel.IDetailPresenter;


/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/14.
 */
public class DetailFragment extends BaseFragment implements IDetailView{

    IDetailPresenter mIDetailPresenter;

    @BindView(R.id.fragment_detail_list)
    PullToRefreshRecyclerView mDetailPullView;

    DetailPullViewAdapter mPullAdapter;

    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,view);
        mIDetailPresenter = new IDetailPresenter(this);

        initDetailPullView();

        return view;
    }

    private void initDetailPullView() {
        mDetailPullView.setSwipeEnable(true);


        View mDetailHeader = LayoutInflater.from(getBottomContext()).inflate(R.layout.header_detail, null);

        mDetailPullView.addHeaderView(mDetailHeader);

        //加载更多
        mDetailPullView.setLayoutManager(new LinearLayoutManager(getBottomContext()));
        mDetailPullView.setPagingableListener(new PullToRefreshRecyclerView.PagingableListener(){
            @Override
            public void onLoadMoreItems() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPullAdapter.setCount(mPullAdapter.getItemCount() + 20);
                        mPullAdapter.notifyDataSetChanged();
                        mDetailPullView.onFinishLoading(true, false);
                    }
                });
            }
        });


        //设置间隔线
        mDetailPullView.getRecyclerView().addItemDecoration(new DividerItemDecoration(getBottomContext(),
                DividerItemDecoration.VERTICAL_LIST));


        //设置上拉加载
        BaseLoadMoreView loadMoreView = new BaseLoadMoreView(getBottomContext(), mDetailPullView.getRecyclerView());
        mDetailPullView.setLoadMoreFooter(loadMoreView);
        mDetailPullView.setLoadMoreCount(20);

        //刷新
        mDetailPullView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        mPullAdapter.setCount(20);
                        mPullAdapter.notifyDataSetChanged();

                        mDetailPullView.setOnRefreshComplete();
                        mDetailPullView.onFinishLoading(true, false);
                    }
                });
            }
        });


        //设置适配器
        mPullAdapter = new DetailPullViewAdapter(getBottomContext());
        mPullAdapter.setOnItemClickLitener(new DetailPullViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showRoomFragment();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


        mPullAdapter.setCount(20);

        mDetailPullView.setAdapter(mPullAdapter);
        mDetailPullView.onFinishLoading(true, false);


    }

    private void showRoomFragment() {
        mStackClickListener.showFragmentByClass(FragConstant.ROOM);
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void showViewToast(String msg) {

    }

    @Override
    public void success(int type) {

    }

    @Override
    public void failed(int type, Throwable e) {

    }
}
