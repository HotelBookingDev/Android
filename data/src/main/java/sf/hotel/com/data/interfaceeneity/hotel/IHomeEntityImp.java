package sf.hotel.com.data.interfaceeneity.hotel;

import rx.Observable;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/29.
 */
public class IHomeEntityImp implements HomeEntity {
    @Override
    public Observable<ProvincesResult> callCityList() {
        return ApiWrapper.getInstance().callCityList();
    }


}
