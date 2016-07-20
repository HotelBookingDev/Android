package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.CityListAdapter;
import sf.hotel.com.hotel_client.view.adapter.OnItemClickListener;
import sf.hotel.com.hotel_client.view.custom.CustomImgText;
import sf.hotel.com.hotel_client.view.custom.CustomSearchItem;
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

    CustomSearchItem mHeaderText;

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
        mICityPresenter.getProcincesResult();
        mICityPresenter.getCityBean();
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(CityMessage.class)
                .subscribe(cityMessage -> {

                }, throwable -> {

                });
        addSubscription(subscribe);
    }

    //    界面排版
    private void initGrid() {


        //设置4列
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBottomContext(), 4);
        mGridRecyclerView.setLayoutManager(gridLayoutManager);
        mCityListAdapter = new CityListAdapter(getBottomContext());
        View header = LayoutInflater.from(getBottomContext()).inflate(R.layout.header_city, null);
        mHeaderText = (CustomSearchItem) header.findViewById(R.id.header_city_location);
        mCityListAdapter.setHeaderView(header);
        mCityListAdapter.setOnTextClickListener(new CityListAdapter.OnTextClickListener() {
            @Override
            public void onTextClick(View view, int pos) {
                mICityPresenter.onTextClick(view, pos);
            }
        });
        mGridRecyclerView.setAdapter(mCityListAdapter);
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }


    @Override
    public void setCityListAdapterDate(List<CityBean> cityBeen) {
        mCityListAdapter.setDatas(cityBeen);
    }

    @Override
    public void setCityListAdapterSelect(CityBean cityBeen) {
        mCityListAdapter.setSelectCityBean(cityBeen);
    }

    @Override
    public void setHeadTextStr(String text){
        mHeaderText.setLeftTextStr(text);
    }


    @Override
    public CityListAdapter getCityListAdapter() {
        return mCityListAdapter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mICityPresenter.destroy();
    }
}
