package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import java.util.List;

import rx.Observable;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public interface CityEntity {
    Observable<List<CityBean>> callCityList();

    List<CityBean> getProcincesResult(Context context);

    void saveCitysBean(Context context, CityBean cityBean);

    CityBean getCityBean(Context bottomContext);
}
