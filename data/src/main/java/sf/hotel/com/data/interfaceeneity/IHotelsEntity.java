package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import sf.hotel.com.data.entity.netresult.HotelResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public interface IHotelsEntity {

    Observable<HotelResult> callHotelsByCityId(String cityId);
}
