package sf.hotel.com.hotel_client.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.hotel.CityActivity;
import sf.hotel.com.hotel_client.view.activity.hotel.TimesActivity;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HomeMessage;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;
import sf.hotel.com.hotel_client.view.fragment.hotel.HotelsFragment;
import sf.hotel.com.hotel_client.view.fragment.hotel.SearchHotelFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IHomeContainerView;
import sf.hotel.com.hotel_client.view.presenter.hotel.IHomePresenter;

/**
 * author MZ
 * email sanfenruxi1@163.com
 * date 16/6/23.
 */
public class HomeContainer extends BaseFragment implements IHomeContainerView {
    public static final int CITY_REQUEST_CODE = 1001;

    @BindView(R.id.custom_title_city)
    View mCityView;

    @BindView(R.id.custom_title_city_name)
    TextView mCityName;

    @BindView(R.id.custom_title_search)
    View mSearchView;
    //每个Presenter 都有一个view对于的视图层
    public IHomePresenter mIHomePresenter;

    SearchItem mSearchItem;

    public static HomeContainer newInstance() {

        Bundle args = new Bundle();
        HomeContainer fragment = new HomeContainer();
        fragment.setArguments(args);

        return fragment;
    }

    @OnClick({R.id.custom_title_city, R.id.custom_title_search})
    public void onClick(View view) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container_home, container, false);
        ButterKnife.bind(this, view);
        mIHomePresenter = new IHomePresenter(this);
        initCache();
        init(savedInstanceState);
        return view;
    }

    private void initCache() {
        //这是加载当前信息
        mIHomePresenter.loadSearchItem();
    }

    private void init(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fragment_container_home_frame,
                    HotelsFragment.newInstance());
        }
    }

    @Override
    public void setTextCityName(String s) {
        mCityName.setText(s);
    }

    public void saveTextCity(CityBean cityBean) {
        mIHomePresenter.saveCityBean(cityBean);
    }

    @Override
    public void setCityBean(CityBean cityBean) {
        mSearchItem.cityBean = cityBean;
    }

    public SearchItem getmSearchItem() {
        return mSearchItem;
    }

    public void setmSearchItem(SearchItem mSearchItem) {
        this.mSearchItem = mSearchItem;
    }
}
