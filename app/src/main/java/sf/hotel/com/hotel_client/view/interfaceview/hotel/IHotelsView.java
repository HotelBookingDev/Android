package sf.hotel.com.hotel_client.view.interfaceview.hotel;

import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public interface IHotelsView  extends BaseView {
    void setHotelAdapterList(HotelResult hotelResult);

    void addHotelAdapterList(HotelResult hotelResult);

    SearchItem getSearchItem();

    void setSearchItem(SearchItem mSearchItem);

    void loadMoreFinish();
    void refreshComplete();
}
