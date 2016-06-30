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
import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.hotel.CityActivity;
import sf.hotel.com.hotel_client.view.activity.hotel.TimesActivity;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HomeMessage;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;
import sf.hotel.com.hotel_client.view.fragment.hotel.HotelsFragment;
import sf.hotel.com.hotel_client.view.presenter.hotel.IHomePresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/23.
 */
public class HomeContainer extends BaseFragment {
    public static final int CITY_REQUEST_CODE = 1001;

    @BindView(R.id.custom_title_city)
    View mCityView;

    @BindView(R.id.custom_title_city_name)
    TextView mCityName;

    @BindView(R.id.custom_title_search)
    View mSearchView;

    public IHomePresenter mIHomePresenter;

    static volatile ProcincesResult.ProcincesBean.CityBean cityBean = new ProcincesResult.ProcincesBean.CityBean();

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

    @OnClick(R.id.custom_title_city)
    public void onCityViewClick() {
        Intent intent = new Intent(getBottomContext(), CityActivity.class);
        startActivityForResult(intent, CITY_REQUEST_CODE);
    }

    @OnClick(R.id.custom_title_search)
    public void onSearchViewClick() {
        Intent intent = new Intent(getBottomContext(), TimesActivity.class);
        startActivity(intent);
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
        mIHomePresenter.callCityList();
        mIHomePresenter.loadCitysBeanCache();
    }

    private void init(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            // loadRootFragment(R.id.fragment_container_hor_home_frame, HotelsRecommendFragment.newInstance());
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
            Bundle bundle = data.getExtras();
            ProcincesResult.ProcincesBean.CityBean cityBean = bundle.getParcelable("city");
            assert cityBean != null;
            setTextCityName(cityBean.getName());
            saveTextCity(cityBean);
            RxBus.getDefault()
                    .post(MessageFactory.createHotelMessage(HotelMessage.REFRESH_LIST_VIEW_HOTEL,
                            cityBean));
        }
    }

    public void setTextCityName(String s) {
        mCityName.setText(s);
    }

    public void saveTextCity(ProcincesResult.ProcincesBean.CityBean cityBean) {
        mIHomePresenter.saveCitysBean(cityBean);
    }

    public static void setCityBean(ProcincesResult.ProcincesBean.CityBean cityBean) {
        HomeContainer.cityBean = cityBean;
    }
}
