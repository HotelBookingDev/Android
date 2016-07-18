package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.utils.LogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public class IBookingEntityImp implements BookingEntity {

    /**
     *
     * @param productId 商品id
     * @param searchItem
     * @return
     */
    public Observable<HotelBookResult> callHotelBook(String productId, SearchItem searchItem){

        Date inTimeDate = searchItem.inTime;
        Date outTimeDate = searchItem.outTime;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String inTime = format.format(inTimeDate);
        String outTime = format.format(outTimeDate);

        String guests = "";
        LogUtils.d(inTime + outTime + "----------");
        return ApiWrapper.getInstance().callHotelBook("100f5982-cfca-4c80-a7cb-7f0b229a06bb", inTime, outTime, guests);
    }

    public SearchItem getSearchItem(Context context){
        return HotelDao.getSearchItem(context);
    }
    public void saveSearchItem(Context context,SearchItem searchItem){
        HotelDao.saveSearchItem(context, searchItem);
    }
}
