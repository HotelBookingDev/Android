package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import sf.hotel.com.data.entity.ProvincesResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
interface CityEntity {
    Observable<ProvincesResult> callCityList();
}
