package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public interface CityEntity {
    Observable<ProvincesResult> callCityList();

    ProvincesResult getProcincesResult(Context context);

    void saveCitysBean(Context context, CityBean cityBean);

    CityBean getCityBean(Context bottomContext);
}
