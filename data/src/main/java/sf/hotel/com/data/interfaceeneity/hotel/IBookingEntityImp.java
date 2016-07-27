package sf.hotel.com.data.interfaceeneity.hotel;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;
import sf.hotel.com.data.entity.BookingBean;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.utils.LogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public class IBookingEntityImp extends DataEntityImp implements BookingEntity {

    public Observable<HotelBookResult> callHotelBook(BookingBean bookingBean){

        int id = bookingBean
                .getRoomBean()
                .getRooms()
                .get(bookingBean.getGroupPos())
                .getRoomPackages()
                .get(bookingBean.getChildPos())
                .getId();

        SearchItem searchItem = bookingBean.getSearchItem();
        Date inTimeDate = searchItem.inTime;
        Date outTimeDate = searchItem.outTime;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String inTime = format.format(inTimeDate);
        String outTime = format.format(outTimeDate);
        String guests = "";
        LogUtils.d(inTime + outTime + "----------");
        String price_type = bookingBean.getPriceType();


        LogUtils.d("-->", id + inTime + outTime + guests + price_type );

        return ApiWrapper.getInstance().callHotelBook(String.valueOf(id), inTime, outTime, guests, price_type);
    }

}
