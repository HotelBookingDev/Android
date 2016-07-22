package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.data.entity.BookingBean;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.hotel_client.R;
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
        initDate();
        return view;
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
