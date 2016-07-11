package sf.hotel.com.hotel_client.view.event.hotel;

import sf.hotel.com.hotel_client.view.event.Message;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public class TimerMessage extends Message {
    public static final int REQUEST_SEARCH_HOTEL = 1000;

    public TimerMessage(int what) {
        super(what);
    }

    public TimerMessage(String message, int what) {
        super(message, what);
    }

    public TimerMessage(int what, String message, Object obj) {
        super(what, message, obj);
    }

}
