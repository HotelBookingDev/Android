package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import com.google.gson.Gson;

import rx.Observable;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public class ISearchHotelEntityImp extends DataEntityImp implements SearchHotelEntity {
    
    @Override
    public Observable<ProvincesResult> callCityList() {
        return ApiWrapper.getInstance().callCityList();
    }

    public void saveProvincesResult(Context context, ProvincesResult result){
        HotelDao.saveProcincesResult(context, result);
    }
}
