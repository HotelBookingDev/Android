package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.CityListAdapter;
import sf.hotel.com.hotel_client.view.custom.CustomSearchItem;
import sf.hotel.com.hotel_client.view.custom.city.CityHotAdapter;
import sf.hotel.com.hotel_client.view.custom.city.CityLayout;
import sf.hotel.com.hotel_client.view.custom.city.CityListAllAdapter;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
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

    CustomSearchItem mHeaderText;

    private ICityPresenter mICityPresenter;

    private GridView mGridView;
    private CityHotAdapter mGridAdapter;

    private List<CityBean> allCityBean;
    private List<CityBean> hotCityBean;

    @BindView(R.id.fragment_city_citylayout)
    CityLayout mCityLayout;

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
       //initGrid();
        initCityLayout();
        onRxEvent();
        initCityCache();
        return view;
    }

    private void initCityLayout() {
        mCityLayout.setOnCityItemClickListener(new CityListAllAdapter.OnCityItemClickListener() {
            @Override
            public void onCityItemClick(View v, int pos) {
                mICityPresenter.onTextClick(v, pos);
            }
        });

        mCityLayout.setHotOnItemClickListener(new CityHotAdapter.OnTextClickListener() {
            @Override
            public void onTextClick(View view, int pos) {
                mICityPresenter.onHotTextClick(view, pos);
            }
        });
    }


    public List<CityBean> getHotCityBean() {
        return hotCityBean;
    }

    public void setHotCityBean(List<CityBean> hotCityBean) {
        this.hotCityBean = hotCityBean;
        mCityLayout.setHotCityBeen(hotCityBean);

    }

    public List<CityBean> getAllCityBean() {
        return allCityBean;
    }

    public void setAllCityBean(List<CityBean> allCityBean) {
        this.allCityBean = allCityBean;
        mCityLayout.setAllCityBeen(allCityBean);
    }


    public void onFinishing(){
        //RxBus.getDefault().post(MessageFactory.createCityMessage(CityMessage.ACTIVITY_FINISH, ""));
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


    @Override
    public Context getBottomContext() {
        return getActivity();
    }



    public void setCurrCityBean(CityBean currCityBean){
        mCityLayout.setSearchCity(currCityBean);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mICityPresenter.destroy();
    }
}
