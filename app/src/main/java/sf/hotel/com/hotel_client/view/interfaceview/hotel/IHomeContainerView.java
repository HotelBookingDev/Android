package sf.hotel.com.hotel_client.view.interfaceview.hotel;

import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望
 * data：2016/6/30
 * email: 1105896230@qq.com
 */
public interface IHomeContainerView extends BaseView {
    void setTextCityName(String cityName);

    void setCityBean(ProvincesResult.ProcincesBean.CityBean mCityBean);
}
