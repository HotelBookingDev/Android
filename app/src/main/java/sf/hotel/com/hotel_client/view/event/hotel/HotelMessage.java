package sf.hotel.com.hotel_client.view.event.hotel;

import java.util.Objects;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/16.
 */

public class HotelMessage extends Message {

    public final static int SHOW_DETAIL_FRAGMENT = 2;
    public final static int SHOW_ROOM_FRAGMENT = 3;

    public final static int FRAGMENT_BACK = 10;

    public final static int SHOW_BOTTOM_VIEW = 100;
    public final static int HIDE_BOTTOM_VIEW = 101;

    public HotelMessage(int what) {
        super(what);
    }

    public HotelMessage(String message, int what) {
        super(message, what);
    }

    public HotelMessage(int what, String message, Objects obj) {
        super(what, message, obj);
    }
}
