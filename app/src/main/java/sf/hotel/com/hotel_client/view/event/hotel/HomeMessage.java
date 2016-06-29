package sf.hotel.com.hotel_client.view.event.hotel;

import sf.hotel.com.hotel_client.view.event.Message;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/23.
 */
public class HomeMessage extends Message {
    public static final int RESULT_CODE_CITY = 1;

    public HomeMessage(int what) {
        super(what);
    }

    public HomeMessage(String message, int what) {
        super(message, what);
    }

    public HomeMessage(int what, String message, Object obj) {
        super(what, message, obj);
    }
}
