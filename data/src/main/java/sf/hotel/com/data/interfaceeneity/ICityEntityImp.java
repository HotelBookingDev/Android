package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ICityEntityImp implements CityEntity {

    public ProcincesResult getProcincesResult(Context context){
        return HotelDao.getProcincesResult(context);
    }

    public void saveCitysBean(Context context, ProcincesResult.ProcincesBean.CitysBean citysBean){
        HotelDao.saveCitysBean(context, citysBean);
    }

    @Override
    public Observable<ProcincesResult> callCityList() {
        return ApiWrapper.getInstance().callCityList();

    }
}
