package sf.hotel.com.data.interfaceeneity.hotel;

import rx.Observable;
import sf.hotel.com.data.entity.ProcincesResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/29.
 */
public interface HomeEntity {
    Observable<ProcincesResult> callCityList();
}
