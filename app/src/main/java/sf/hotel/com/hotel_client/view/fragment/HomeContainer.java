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
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.hotel.CityActivity;
import sf.hotel.com.hotel_client.view.activity.hotel.TimesActivity;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HomeMessage;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;
import sf.hotel.com.hotel_client.view.fragment.hotel.HotelsFragment;
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

    static volatile CityBean cityBean = new CityBean();

    //TODO 提取个方法 如果对象没回收了 加载的时候需要做判断，不让为null
    static {
        cityBean.setId(1);
        cityBean.setName("杭州");
    }

    public static HomeContainer newInstance() {

        Bundle args = new Bundle();
        HomeContainer fragment = new HomeContainer();
        fragment.setArguments(args);

        return fragment;
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(HomeMessage.class)
                .subscribe(homeMessage -> {

                }, throwable -> {

                });
        mCompositeSubscription.add(subscribe);
    }

    @OnClick({R.id.custom_title_city, R.id.custom_title_search})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.custom_title_city) {
            onCityViewClick();
        } else if (id == R.id.custom_title_search) {
            onSearchViewClick();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container_home, container, false);
        ButterKnife.bind(this, view);
        mIHomePresenter = new IHomePresenter(this);

        init(savedInstanceState);
        onRxEvent();

        initView();
        return view;
    }

    private void initView() {
        //这是加载当前信息
        mIHomePresenter.loadCitysBeanCache();
        //进行网络请求获取城市列表信息，会有问题 你请求的时候你加载后面城市列表的时候信息不会到达，容器是不知道要去做加载城市列报需要内部去实现
        mIHomePresenter.callCityList();
    }

    private void init(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fragment_container_home_frame,
                    HotelsFragment.newInstance(cityBean));
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            super.onBackPressedSupport();
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == CITY_REQUEST_CODE) {
            CityBean cityBean = mIHomePresenter.getCityBean();
            if (cityBean != null && cityBean.getName()!= null){
                HomeContainer.cityBean = cityBean;
            }
            setTextCityName(HomeContainer.cityBean.getName());
            RxBus.getDefault()
                    .post(MessageFactory.createHotelMessage(HotelMessage.REFRESH_LIST_VIEW_HOTEL,
                            HomeContainer.cityBean));
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
        HomeContainer.cityBean = cityBean;
    }

    public void onCityViewClick() {
        Intent intent = new Intent(getBottomContext(), CityActivity.class);
        startActivityForResult(intent, CITY_REQUEST_CODE);
    }

    public void onSearchViewClick() {
        Intent intent = new Intent(getBottomContext(), TimesActivity.class);
        startActivity(intent);
    }
}
