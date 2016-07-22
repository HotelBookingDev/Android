package sf.hotel.com.data.interfaceeneity.hotel;

import rx.Observable;
import sf.hotel.com.data.entity.BookingBean;
import sf.hotel.com.data.entity.HotelBookResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public interface BookingEntity extends DataEntity{
    Observable<HotelBookResult> callHotelBook(BookingBean bookingBean);

}
