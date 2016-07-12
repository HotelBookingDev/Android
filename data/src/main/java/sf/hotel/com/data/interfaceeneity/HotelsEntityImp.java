package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HotelsEntityImp implements IHotelsEntity {


    public HotelResult getHotelCache(Context context){
        return HotelDao.getHotelResult(context);
    }

    public void saveHotelCache(Context context, HotelResult hotelResult){
        HotelDao.saveHotelResult(context, hotelResult);
    }

    @Override
    public Observable<HotelResult> callHotelsByCityId(String cityId, String page) {
        return ApiWrapper.getInstance()
                .callHotelsByCityId(cityId, page, "", "", "")
                ;
    }
}
