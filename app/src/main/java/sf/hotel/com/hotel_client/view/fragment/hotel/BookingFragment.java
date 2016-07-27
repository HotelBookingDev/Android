package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.data.entity.BookingBean;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.adapter.BaseRecyclerAdapter;
import sf.hotel.com.hotel_client.view.adapter.BookingSearchAdapter;
import sf.hotel.com.hotel_client.view.custom.CustomBookingSearch;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IBookingView;
import sf.hotel.com.hotel_client.view.presenter.hotel.IBookingPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public class BookingFragment extends BaseFragment implements IBookingView {

    IBookingPresenter mIBookingPresenter;


    @BindView(R.id.fragment_booking_pay)
    FancyButton mPay;

    BookingBean bookingBean;


    @BindView(R.id.fragment_booking_search_time)
    CustomBookingSearch bookingTimer;

    @BindView(R.id.fragment_booking_bank)
    CustomBookingSearch bookingBank;


    List<String> timerList;
    List<String> bankList;


    BookingSearchAdapter timeAdapter, bankAdapter;



    public static BookingFragment newInstance() {
        Bundle args = new Bundle();
        BookingFragment fragment = new BookingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_booking, container, false);
        ButterKnife.bind(this, view);
        mIBookingPresenter = new IBookingPresenter(this);
        initSearch();

        initDate();
        return view;
    }

    private void initSearch() {
        timerList = new ArrayList<>();
        timerList.add("14:00");
        timerList.add("15:00");
        timerList.add("16:00");
        timerList.add("17:00");
        timerList.add("18:00");
        timerList.add("19:00");

        timeAdapter = new BookingSearchAdapter(getBottomContext());
        timeAdapter.setDatas(timerList);


        timeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                bookingTimer.setContentText(timerList.get(position));
                bookingTimer.hidePopWindow();
            }
        });

        bankList = new ArrayList<>();
        bankList.add("招商银行");
        bankList.add("建设银行");

        bankAdapter = new BookingSearchAdapter(getBottomContext());
        bankAdapter.setDatas(bankList);

        bankAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                bookingBank.setContentText(bankList.get(position));
                bookingBank.hidePopWindow();
            }
        });

        bookingTimer.setAdapter(timeAdapter);
        bookingBank.setAdapter(bankAdapter);
    }

    private void initDate() {
        mIBookingPresenter.getBookingBean();
        notifyDataSetChanged();
    }

    @OnClick({R.id.fragment_booking_pay})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.fragment_booking_pay:
                mIBookingPresenter.callHotelBook();
                break;
        }
    }

    public BookingBean getBookingBean() {
        return bookingBean;
    }

    public void setBookingBean(BookingBean bookingBean) {
        this.bookingBean = bookingBean;
    }

    public void notifyDataSetChanged() {


    }

}
