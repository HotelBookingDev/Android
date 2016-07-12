package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.CityBean;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public class ISearchHotelEntityImp implements SearchHotelEntity {

    public CityBean getCityBean(Context context){
        return HotelDao.getCitysBean(context);
    }
}
