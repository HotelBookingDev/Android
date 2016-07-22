package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import java.util.List;

import sf.hotel.com.data.entity.BookingBean;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.UserEntity;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/20.
 */
public interface DataEntity {
    SearchItem getSearchItem(Context context);
    void saveSearchItem(Context context,SearchItem searchItem);
    List<CityBean> getProcincesResult(Context context);
    void saveCitysBean(Context context, CityBean cityBean);
    CityBean getCityBean(Context bottomContext);

    BookingBean getBookingBean(Context context);
    void saveBookingBean(Context context, BookingBean bookingBean);

    UserEntity getUserEntity(Context context);

}
