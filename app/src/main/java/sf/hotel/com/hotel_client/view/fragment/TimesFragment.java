package sf.hotel.com.hotel_client.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.net.SelectDates;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.caledarlistview.DatePickerController;
import sf.hotel.com.hotel_client.view.custom.caledarlistview.DayPickerView;
import sf.hotel.com.hotel_client.view.custom.caledarlistview.SimpleMonthAdapter;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.TimerMessage;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ITimerView;
import sf.hotel.com.hotel_client.view.presenter.hotel.TimerPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/27.
 */
public class TimesFragment extends BaseFragment implements ITimerView{

    @BindView(R.id.fragment_times_calendar_view)
    DayPickerView mCalendarView;

    @BindView(R.id.fragment_times_submit)
    FancyButton mSubmit;

    TimerPresenter mTimerPresenter;

    SearchItem searchItem;

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
        mTimerPresenter = new TimerPresenter(this);
        initDate();
        initCalendarView();
        return view;
    }

    private void initDate() {
        mTimerPresenter.getSearchItem();
    }


    @OnClick(R.id.fragment_times_submit)
    public void onSubmitClick() {
        SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays = mCalendarView.getSelectedDays();
        SelectDates selectDates = new SelectDates();
        if (selectedDays.getFirst() != null
                && selectedDays.getLast() != null){
            if (selectedDays.getFirst().getDate() != null
                    && selectedDays.getLast().getDate() != null){
                selectDates.dates.add(selectedDays.getFirst().getDate());
                selectDates.dates.add(selectedDays.getLast().getDate());
            }

        }

        RxBus.getDefault().post(MessageFactory.createTimerMessage(TimerMessage.REQUEST_SEARCH_HOTEL, selectDates));

     //   LogUtils.d("--->", selectedDates.toString());
    }

    private void initCalendarView() {
        mCalendarView.setController(new DatePickerController() {
            @Override
            public int getMaxYear() {
                return 0;
            }

            @Override
            public int getMaxMonth() {
                return 1;
            }

            @Override
            public Date getStartTime() {
                return null;
            }

            @Override
            public void onDayOfMonthSelected(int year, int month, int day) {
            }
            @Override
            public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {

            }

            @Override
            public SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> setOnStartSelectedDays() {

                SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays = new SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay>();

                selectedDays.setFirst(new SimpleMonthAdapter.CalendarDay(searchItem.inTime.getTime()));
                selectedDays.setLast(new SimpleMonthAdapter.CalendarDay(searchItem.outTime.getTime()));

                return selectedDays;
            }
        });
    }

    public SearchItem getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(SearchItem searchItem) {
        this.searchItem = searchItem;
    }
}
