package sf.hotel.com.data.interfaceeneity.hotel;


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
public class IRoomEntityImp extends DataEntityImp implements RoomEntity{


    /**
     * @param id hotel的id
     * @return
     */
    public Observable<RoomBean> callHotelBean(String id, SearchItem searchItem, String ex){

//        String inTime = searchItem.inTime;
//        String outTime = searchItem.outTime.getTime()
//
        return ApiWrapper.getInstance().callHotelBean(id, "", "", ex);
    }
}
