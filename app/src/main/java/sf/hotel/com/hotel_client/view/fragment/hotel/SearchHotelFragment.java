package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;
import mehdi.sakout.fancybuttons.FancyButton;
import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.hotel.HotelsActivity;
import sf.hotel.com.hotel_client.view.activity.hotel.TimesActivity;
import sf.hotel.com.hotel_client.view.custom.CustomTimerView;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.SearchHotelMessage;
import sf.hotel.com.hotel_client.view.event.hotel.TimerMessage;
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

    @BindView(R.id.search_hotel)
    FancyButton search_hotel;

    @BindView(R.id.fragment_search_hotel_timer)
    CustomTimerView mTimerView;

    ISearchHotelPresenter mISearchHotelPresenter;

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

        mISearchHotelPresenter = new ISearchHotelPresenter(this);
        ButterKnife.bind(this, view);

        onRxEvent();

        return view;
    }

    private void onRxEvent() {

        Subscription subscribe = RxBus.getDefault().toObservable(SearchHotelMessage.class).subscribe(new Action1<SearchHotelMessage>() {
            @Override
            public void call(SearchHotelMessage searchHotelMessage) {

            }
        });

        mCompositeSubscription.add(subscribe);

    }

    @OnClick({R.id.search_hotel,
            R.id.fragment_search_hotel_timer})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.search_hotel:
                showHotel();
                break;
            case R.id.fragment_search_hotel_timer:

                showTimer();

                break;
        }

    }

    private void showTimer() {
        Intent intent = new Intent(getBottomContext(), TimesActivity.class);
        intent.putExtra("action", "search_hotel");
        startActivityForResult(intent, REQUEST_TIMER);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TIMER){

        }
    }

    private void showHotel() {
        Intent intent = new Intent(getBottomContext(), HotelsActivity.class);

        startActivity(intent);
    }


}
