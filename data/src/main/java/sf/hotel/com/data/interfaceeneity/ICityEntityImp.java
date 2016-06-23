package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ICityEntityImp implements CityEntity {
    @Override
    public Observable<ProcincesResult> callCityList() {
        return ApiWrapper.getInstance().callCityList();
    }
}
