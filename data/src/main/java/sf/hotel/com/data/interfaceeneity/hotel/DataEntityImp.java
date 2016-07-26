package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import java.util.List;

import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.datasource.UserDao;
import sf.hotel.com.data.entity.BookingBean;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/20.
 */
public abstract class DataEntityImp implements DataEntity{
    public SearchItem getSearchItem(Context context){
        return HotelDao.getSearchItem(context);
    }
    public void saveSearchItem(Context context,SearchItem searchItem){
        HotelDao.saveSearchItem(context, searchItem);
    }

    public CityBean getCityBean(Context context){
        return HotelDao.getCitysBean(context);
    }

    @Override
    public BookingBean getBookingBean(Context context) {
        return HotelDao.getBookingBean(context);
    }


    @Override
    public UserEntity getUserEntity(Context context) {
        long userId = PreferencesUtils.getUserId(context);
        return UserDao.getUserEntity(userId, context);
    }

    @Override
    public void saveBookingBean(Context context, BookingBean bookingBean) {
        HotelDao.saveBookingBean(context, bookingBean);
    }

    @Override
    public void clearBookingBean(Context context){
        HotelDao.clearBookingBean(context);
    }

    public List<CityBean> getProcincesResult(Context context){
        List<CityBean> citys = null;

        ProvincesResult procincesResult = HotelDao.getProcincesResult(context);
        List<ProvincesBean> procinces = procincesResult.getProvinces();
        for (ProvincesBean procincesBean : procinces) {
            citys = procincesBean.getCitys();
        }
        return citys;
    }

    public void saveCitysBean(Context context, CityBean cityBean){
        HotelDao.saveCitysBean(context, cityBean);
    }



}
