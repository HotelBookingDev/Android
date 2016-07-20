package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import java.text.SimpleDateFormat;

import rx.Observable;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HotelsEntityImp extends DataEntityImp implements IHotelsEntity {

    public HotelResult getHotelCache(Context context){
        return HotelDao.getHotelResult(context);
    }

    public void saveHotelCache(Context context, HotelResult hotelResult){
        HotelDao.saveHotelResult(context, hotelResult);
    }

    @Override
    public Observable<HotelResult> callHotelsByCityId(SearchItem searchItem, String page, String exclude) {

        String cityId = String.valueOf(searchItem.cityBean.getCode());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String inTime = format.format(searchItem.inTime);
        String outTime = format.format(searchItem.outTime);


        return ApiWrapper.getInstance()
                .callHotelsByCityId(cityId, page, inTime, outTime, exclude)
                ;
    }
}
