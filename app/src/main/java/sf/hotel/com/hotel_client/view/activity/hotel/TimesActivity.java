package sf.hotel.com.hotel_client.view.activity.hotel;

import android.content.Intent;
import android.os.Bundle;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.TimerMessage;
import sf.hotel.com.hotel_client.view.fragment.TimesFragment;
import sf.hotel.com.hotel_client.view.fragment.hotel.SearchHotelFragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/27.
 */
public class TimesActivity extends BaseActivity {

    @BindView(R.id.activity_times_back)
    HotelTitleView mHotelTitleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);
        ButterKnife.bind(this);

        onRxEvent();

        Intent intent = getIntent();
        String action = intent.getStringExtra("action");

        if ("search_hotel".equals(action)){
            init();
        }
        mHotelTitleView.addLeftClick(v -> finish());
    }

    private void onRxEvent() {
        RxBus.getDefault().toObservable(TimerMessage.class)
                .subscribe(new Action1<TimerMessage>() {
            @Override
            public void call(TimerMessage timerMessage) {
                switch (timerMessage.what){
                    case TimerMessage.REQUEST_SEARCH_HOTEL:
                        List<Date> selectedDates = (List<Date>) timerMessage.obj;
                        Date[] dates = new Date[2];
                        if (selectedDates.size() > 1) {
                            dates[0] = selectedDates.get(0);
                            dates[1] = selectedDates.get(selectedDates.size() - 1);
                        }
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("dates", dates);
                        intent.putExtras(bundle);
                        setResult(SearchHotelFragment.REQUEST_TIMER, intent);
                        finish();
                        break;
                }
            }
        });
    }

    private void init() {
        loadRootFragment(R.id.activity_times_frame, TimesFragment.newInstance());
    }



}
