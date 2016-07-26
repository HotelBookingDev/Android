package sf.hotel.com.hotel_client.view.interfaceview.hotel;

import java.util.List;

import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.hotel_client.view.adapter.CityListAdapter;
import sf.hotel.com.hotel_client.view.custom.city.CityListAllAdapter;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.CityMessage;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public interface ICityView extends BaseView {

    List<CityBean> getHotCityBean();

    void setHotCityBean(List<CityBean> hotCityBean);

    List<CityBean> getAllCityBean();

    void setAllCityBean(List<CityBean> allCityBean);

    void onFinishing();

    void setCurrCityBean(CityBean currCityBean);
}
