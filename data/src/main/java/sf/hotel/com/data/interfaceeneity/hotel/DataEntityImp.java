package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import java.util.List;

import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.SearchItem;

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
