package sf.hotel.com.hotel_client.view.interfaceview.hotel;

import java.util.Date;

import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public interface ISearchHotelView extends BaseView {
    void setTextCityName(String name);

    SearchItem getSearchItem();

    void setSearchItem(SearchItem mSearchItem);

    void setSearchTimer(Date[] dates);

    void setCityBean(CityBean cityBean);

    Date[] getSearchTimer();
}
