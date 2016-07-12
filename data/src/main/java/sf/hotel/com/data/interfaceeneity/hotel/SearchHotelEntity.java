package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import com.google.gson.Gson;

import rx.Observable;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public interface SearchHotelEntity {

    CityBean getCityBean(Context context);

    SearchItem getSearchItem(Context context);

    void saveSearchItem(Context context, SearchItem searchItem);

    Observable<ProvincesResult> callCityList();
}
