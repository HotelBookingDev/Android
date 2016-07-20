package sf.hotel.com.data.interfaceeneity.hotel;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.hotel.Hotel1Result;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomBean;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/12.
 */
public interface RoomEntity extends DataEntity{
    /**
     * @param id hotel的id
     * @return
     */
    Observable<RoomBean> callHotelBean(Context context ,String id, String ex);
}
