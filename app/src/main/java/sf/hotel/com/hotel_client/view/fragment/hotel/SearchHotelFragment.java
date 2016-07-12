package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.hotel.CityActivity;
import sf.hotel.com.hotel_client.view.activity.hotel.HotelsActivity;
import sf.hotel.com.hotel_client.view.activity.hotel.TimesActivity;
import sf.hotel.com.hotel_client.view.custom.CustomSearchItem;
import sf.hotel.com.hotel_client.view.custom.CustomTimerView;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ISearchHotelView;
import sf.hotel.com.hotel_client.view.presenter.hotel.ISearchHotelPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/6.
 */
public class SearchHotelFragment extends BaseFragment implements ISearchHotelView {

    public static final int REQUEST_TIMER = 1001;
    public static final int REQUEST_CITY = 1002;

    @BindView(R.id.search_hotel)
    Button search_hotel;

    @BindView(R.id.fragment_search_hotel_timer)
    CustomTimerView mTimerView;

    @BindView(R.id.fragment_search_hotel_city)
    CustomSearchItem mSearchCity;

    ISearchHotelPresenter mISearchHotelPresenter;

    SearchItem mSearchItem;

    public static SearchHotelFragment newInstance() {

        Bundle args = new Bundle();
        SearchHotelFragment fragment = new SearchHotelFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_search_hotle, container, false);
        ButterKnife.bind(this, view);
        mISearchHotelPresenter = new ISearchHotelPresenter(this);
        initSearchItem();

        return view;
    }

    private void initSearchItem() {
        mSearchItem = new SearchItem();
        mISearchHotelPresenter.loadSearchItem();
        mISearchHotelPresenter.callCityList();
    }

    @OnClick({R.id.search_hotel,
            R.id.fragment_search_hotel_timer,
            R.id.fragment_search_hotel_city})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.search_hotel:
                showHotel();
                break;
            case R.id.fragment_search_hotel_timer:
                showTimer();
                break;
            case R.id.fragment_search_hotel_city:
                showCities();
                break;
        }

    }

    private void showCities() {
        Intent intent = new Intent(getBottomContext(), CityActivity.class);
        intent.putExtra("action", "search_hotel");
        startActivityForResult(intent, REQUEST_CITY);
    }

    private void showTimer() {
        Intent intent = new Intent(getBottomContext(), TimesActivity.class);
        intent.putExtra("action", "search_hotel");
        startActivityForResult(intent, REQUEST_TIMER);
    }

    private void showHotel() {

        mISearchHotelPresenter.saveSearchItem();
        Intent intent = new Intent(getBottomContext(), HotelsActivity.class);
        intent.putExtra("action", "search_hotel");
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TIMER && data != null) {
            mISearchHotelPresenter.getSearchDate(data);
        }else if (requestCode == REQUEST_CITY){
            mISearchHotelPresenter.getCityBean();
        }
    }

    @Override
    public void setTextCityName(String name) {
        mSearchCity.setLeftTextStr(name);
    }

    @Override
    public void setSearchTimer(Date[] dates){
        mTimerView.setTimer(dates[0], dates[1]);
    }

    @Override
    public SearchItem getSearchItem() {
        return mSearchItem;
    }

    public void setSearchItem(SearchItem mSearchItem) {
        this.mSearchItem = mSearchItem;
    }

    @Override
    public void setCityBean(CityBean cityBean){
        mSearchItem.cityBean = cityBean;
    }
}
