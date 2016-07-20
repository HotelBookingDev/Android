package sf.hotel.com.data.interfaceeneity.hotel;


import android.content.Context;

import java.text.SimpleDateFormat;

import rx.Observable;
import rx.Subscriber;
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
    public Observable<RoomBean> callHotelBean(Context context, String id, String ex){

        return Observable.create(new Observable.OnSubscribe<RoomBean>() {
            @Override
            public void call(Subscriber<? super RoomBean> subscriber) {
                SearchItem searchItem = getSearchItem(context);
                if (searchItem == null){
                    subscriber.onError(new NullPointerException("searchItem = null"));
                }else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");



                    ApiWrapper.getInstance().callHotelBean(id, "", "", ex).subscribe(subscriber);
                }
            }
        });


    }
}
