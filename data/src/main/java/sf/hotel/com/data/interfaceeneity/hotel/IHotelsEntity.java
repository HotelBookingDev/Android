package sf.hotel.com.data.interfaceeneity.hotel;

import rx.Observable;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.HotelResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
interface IHotelsEntity extends DataEntity{
    Observable<HotelResult> callHotelsByCityId(SearchItem searchItem,String page, String exclude);
}
