package sf.hotel.com.hotel_client.view.event.hotel;

import sf.hotel.com.hotel_client.view.event.Message;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/16.
 */

public class HotelMessage extends Message {

    public final static int SHOW_ROOM_FRAGMENT = 3;
    public static final int REFRESH_LIST_VIEW_HOTEL = 100;

    public static final int ACTIVITY_FINISH = 1001;

    public HotelMessage(int what) {
        super(what);
    }

    public HotelMessage(String message, int what) {
        super(message, what);
    }

    public HotelMessage(int what, String message, Object obj) {
        super(what, message, obj);
    }
}
