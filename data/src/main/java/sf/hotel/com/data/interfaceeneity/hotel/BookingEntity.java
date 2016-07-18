package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public interface BookingEntity {
    Observable<HotelBookResult> callHotelBook( String productId, SearchItem searchItem);
    SearchItem getSearchItem(Context context);
    void saveSearchItem(Context context,SearchItem searchItem);

}
