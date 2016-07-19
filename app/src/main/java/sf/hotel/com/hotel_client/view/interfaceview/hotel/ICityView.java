package sf.hotel.com.hotel_client.view.interfaceview.hotel;

import java.util.List;

import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.hotel_client.view.adapter.CityListAdapter;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public interface ICityView extends BaseView {

    void setCityListAdapterDate(List<CityBean> cityBeen);

    void setCityListAdapterSelect(CityBean cityBeen);

    void setHeadTextStr(String text);

    CityListAdapter getCityListAdapter();
}
