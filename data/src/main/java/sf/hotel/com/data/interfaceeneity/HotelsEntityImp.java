package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HotelsEntityImp implements IHotelsEntity {

    @Override
    public Observable<HotelResult> callHotelsByCityId(String cityId) {
        return ApiWrapper.getInstance().callHotelsByCityId(cityId);
    }
}
