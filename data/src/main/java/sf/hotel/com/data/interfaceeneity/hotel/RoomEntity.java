package sf.hotel.com.data.interfaceeneity.hotel;

import rx.Observable;
import sf.hotel.com.data.entity.HotelBookResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/12.
 */
public interface RoomEntity {
    Observable<HotelBookResult> callHotelBook(String auth, String productId, String inTime, String outTime);
}
