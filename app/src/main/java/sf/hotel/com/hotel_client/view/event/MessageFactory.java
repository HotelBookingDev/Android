package sf.hotel.com.hotel_client.view.event;

import android.util.LruCache;

import sf.hotel.com.hotel_client.view.event.hotel.CityMessage;
import sf.hotel.com.hotel_client.view.event.hotel.HomeMessage;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;
import sf.hotel.com.hotel_client.view.event.hotel.RoomMessage;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.event.person.OrderMessage;
import sf.hotel.com.hotel_client.view.event.person.PersonMessage;

/**
 * Created by 林其望
 * data：2016/6/17
 * email: 1105896230@qq.com
 */
public class MessageFactory {

    LruCache<Integer, Message> msgList;

    public static LoginMessage createLoginMessage(int type) {
        //可以做个缓冲池
        return new LoginMessage(type);
    }

    public static PersonMessage createPersonMessage(int type) {
        return new PersonMessage(type);
    }

    public static HotelMessage createHotelMessage(int what) {
        return new HotelMessage(what);
    }


    public static HotelMessage createHotelMessage(int what, Object obj) {
        return new HotelMessage(what, "", obj);
    }

    public static CityMessage createCityMessage(int what, Object obj) {
        return new CityMessage(what, "", obj);
    }

    public static HomeMessage createHomeMessage(int what, Object obj) {
        return new HomeMessage(what, "", obj);
    }

    public static RoomMessage createRoomMessage(int what, Object obj) {
        return new RoomMessage(what, "", obj);
    }

    public static OrderMessage createOrderMessage(int what) {
        return new OrderMessage(what);
    }
}
