package sf.hotel.com.hotel_client.view.event.hotel;

/**
 * Created by 林其望 on 2016/6/17.
 */
public class MessageFactory {
    public static LoginMessage getLoginMessage(int type) {
        //可以做个缓冲池
        return new LoginMessage(type);
    }
}
