package sf.hotel.com.data.interfaceeneity.hotel;

import rx.Observable;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.netresult.hotel.Hotel1Result;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/12.
 */
public interface RoomEntity {
    Observable<HotelBookResult> callHotelBook(String auth, String productId, String inTime, String outTime);


    /**
     * @param id hotel的id
     * @return
     */
    Observable<Hotel1Result> callHotelBean(String id);
}
