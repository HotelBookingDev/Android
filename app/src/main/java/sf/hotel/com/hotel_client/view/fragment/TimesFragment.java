package sf.hotel.com.hotel_client.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.DefaultDayViewAdapter;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.data.net.SelectDates;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.TimerMessage;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/27.
 */
public class TimesFragment extends BaseFragment {

    @BindView(R.id.fragment_times_calendar_view)
    CalendarPickerView mCalendarView;

    @BindView(R.id.fragment_times_submit)
    FancyButton mSubmit;

    public static TimesFragment newInstance() {

        Bundle args = new Bundle();

        TimesFragment fragment = new TimesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_times, container, false);
        ButterKnife.bind(this, view);
        initCalendarView();
        return view;
    }


    @OnClick(R.id.fragment_times_submit)
    public void onSubmitClick() {
        List<Date> selectedDates = mCalendarView.getSelectedDates();

        SelectDates selectDates = new SelectDates();
        selectDates.dates = selectedDates;

        RxBus.getDefault().post(MessageFactory.createTimerMessage(TimerMessage.REQUEST_SEARCH_HOTEL, selectDates));

     //   LogUtils.d("--->", selectedDates.toString());
    }

    private void initCalendarView() {

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);

        mCalendarView.setCustomDayView(new DefaultDayViewAdapter());

        mCalendarView.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        mCalendarView.init(new Date(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.RANGE);//
    }
}
