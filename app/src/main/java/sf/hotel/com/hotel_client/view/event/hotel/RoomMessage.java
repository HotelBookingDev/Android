package sf.hotel.com.hotel_client.view.event.hotel;

import sf.hotel.com.hotel_client.view.event.Message;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/24.
 */
public class RoomMessage extends Message {

    public static final int INTENT_ROOM = 1;

    public static final int ACTIVITY_BACK = 100;

    public RoomMessage(int what) {
        super(what);
    }

    public RoomMessage(String message, int what) {
        super(message, what);
    }

    public RoomMessage(int what, String message, Object obj) {
        super(what, message, obj);
    }
}
