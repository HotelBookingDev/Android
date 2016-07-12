package sf.hotel.com.hotel_client.view.interfaceview.hotel;

import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望
 * data：2016/6/30
 * email: 1105896230@qq.com
 */
public interface IHomeContainerView extends BaseView {

    void setCityBean(CityBean mCityBean);

    SearchItem getmSearchItem();

    void setmSearchItem(SearchItem mSearchItem);
}
