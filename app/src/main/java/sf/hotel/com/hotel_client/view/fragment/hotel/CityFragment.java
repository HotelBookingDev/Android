package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.CityListAdapter;
import sf.hotel.com.hotel_client.view.adapter.OnItemClickListener;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.CityMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ICityView;
import sf.hotel.com.hotel_client.view.presenter.hotel.ICityPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class CityFragment extends BaseFragment implements ICityView {

    @BindView(R.id.fragment_city_grid)
    RecyclerView mGridRecyclerView;

    private CityListAdapter mCityListAdapter;

    private ICityPresenter mICityPresenter;

    public static CityFragment newInstance() {

        Bundle args = new Bundle();

        CityFragment fragment = new CityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        ButterKnife.bind(this, view);
        mICityPresenter = new ICityPresenter(this);
        initGrid();
        onRxEvent();
        initCityCache();
        return view;
    }

    private void initCityCache() {
// 取消if else 每次都去网络请求
        //TODO 需要考虑何时该去做更新，可能以后登录服务器会给你已给值在那个值做判断
        ProvincesResult provincesResult = mICityPresenter.getProcincesResult(getBottomContext());
        if (provincesResult != null) {
            mCityListAdapter.setList(provincesResult);
        }
        initCityList();
    }

    private void onRxEvent() {
        RxBus.getDefault().toObservable(CityMessage.class).subscribe(cityMessage -> {

        }, throwable -> {

        });
    }

    private void initCityList() {
        mICityPresenter.callCityList();
    }

    //    界面排版
    private void initGrid() {
        //设置3列
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBottomContext(), 3);
        mGridRecyclerView.setLayoutManager(gridLayoutManager);
        mCityListAdapter = new CityListAdapter(getBottomContext());
        mCityListAdapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                mICityPresenter.saveSelectCity(mCityListAdapter
                        .getListItem(mCityListAdapter.isCheckedPos));

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mGridRecyclerView.setAdapter(mCityListAdapter);
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    public void setCityAdapterList(ProvincesResult provincesResult) {
        mCityListAdapter.setList(provincesResult);
    }
}
