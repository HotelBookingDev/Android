package sf.hotel.com.hotel_client.view.interfaceview.hotel;

import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.hotel.room.HotelBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomResult;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/16.
 */
public interface IRoomView extends BaseView {
    int getHotelId();

    void setRoomContentText(String text);

    void setHotelsBean(RoomResult hotel1Bean);

    void notifyDataSetChanged();

    HotelBean getHotelsBean();


    SearchItem getSearchItem();

    void setSearchItem(SearchItem searchItem);

}
