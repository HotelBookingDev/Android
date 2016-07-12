package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.HotelResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
interface IHotelsEntity {

    Observable<HotelResult> callHotelsByCityId(SearchItem searchItem,String page, String exclude);
    SearchItem getSearchItem(Context context);
    void saveSearchItem(Context context,SearchItem searchItem);
}
