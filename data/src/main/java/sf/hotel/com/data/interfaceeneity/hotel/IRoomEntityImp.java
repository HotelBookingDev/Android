package sf.hotel.com.data.interfaceeneity.hotel;


import rx.Observable;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.netresult.hotel.Hotel1Result;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/12.
 */
public class IRoomEntityImp implements RoomEntity{


    /**
     * @param id hotel的id
     * @return
     */
    public Observable<Hotel1Result> callHotelBean(String id, String ex){
        return ApiWrapper.getInstance().callHotelBean(id, ex);
    }
}
