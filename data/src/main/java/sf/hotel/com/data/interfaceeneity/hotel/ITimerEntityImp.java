package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.SearchItem;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/18.
 */
public class ITimerEntityImp implements TimerEntity {

    public SearchItem getSearchItem(Context context){
        return HotelDao.getSearchItem(context);
    }
    public void saveSearchItem(Context context,SearchItem searchItem){
        HotelDao.saveSearchItem(context, searchItem);
    }
}
