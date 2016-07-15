package sf.hotel.com.data.interfaceeneity.hotel;

import rx.Observable;
import sf.hotel.com.data.entity.HotelBookResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public interface BookingEntity {

    /**
     * @param productId 商品id
     * @param inTime 开始时间
     * @param outTime 结束时间
     * @return
     */
    Observable<HotelBookResult> callHotelBook(String productId, String inTime, String outTime);

}
