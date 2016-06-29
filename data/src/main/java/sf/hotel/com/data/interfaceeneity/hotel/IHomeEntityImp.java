package sf.hotel.com.data.interfaceeneity.hotel;

import rx.Observable;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/29.
 */
public class IHomeEntityImp implements HomeEntity {
    @Override
    public Observable<ProcincesResult> callCityList() {
        return ApiWrapper.getInstance().callCityList();
    }


}
