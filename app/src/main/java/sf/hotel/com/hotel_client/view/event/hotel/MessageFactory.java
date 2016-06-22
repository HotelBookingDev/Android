package sf.hotel.com.hotel_client.view.event.hotel;

/**
 * Created by 林其望 on 2016/6/17.
 */
public class MessageFactory {
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
}
