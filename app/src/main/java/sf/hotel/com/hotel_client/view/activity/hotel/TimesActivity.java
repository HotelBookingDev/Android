package sf.hotel.com.hotel_client.view.activity.hotel;

import android.content.Intent;
import android.os.Bundle;

import com.avos.avoscloud.LogUtil;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.data.net.SelectDates;
import sf.hotel.com.data.utils.LogUtils;
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
        Subscription subscription = RxBus.getDefault().toObservable(TimerMessage.class)
                .subscribe(new Action1<TimerMessage>() {
                    @Override
                    public void call(TimerMessage timerMessage) {
                        switch (timerMessage.what) {
                            case TimerMessage.REQUEST_SEARCH_HOTEL:
                                SelectDates selectDates = (SelectDates) timerMessage.obj;

                                Date[] dates = new Date[2];

                                if (selectDates.dates != null && selectDates.dates.size() > 1) {
                                    dates[0] = selectDates.dates.get(0);
                                    dates[1] = selectDates.dates.get(selectDates.dates.size() - 1);
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
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        LogUtils.d("------>", "hahahhaahaha");
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    private void init() {
        loadRootFragment(R.id.activity_times_frame, TimesFragment.newInstance());
    }



}
