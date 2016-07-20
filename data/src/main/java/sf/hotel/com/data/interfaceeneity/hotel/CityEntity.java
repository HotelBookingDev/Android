package sf.hotel.com.data.interfaceeneity.hotel;

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
public interface CityEntity extends DataEntity{
    Observable<List<CityBean>> callCityList();

}
