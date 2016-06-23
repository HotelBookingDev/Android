package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import sf.hotel.com.data.entity.ProcincesResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public interface CityEntity {
    Observable<ProcincesResult> callCityList();
}
