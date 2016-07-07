package sf.hotel.com.hotel_client.view.event.hotel;

import sf.hotel.com.hotel_client.view.event.Message;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public class SearchHotelMessage extends Message {

    public static final int REQUEST_TIMER = 1001;



    public SearchHotelMessage(int what) {
        super(what);
    }

    public SearchHotelMessage(String message, int what) {
        super(message, what);
    }

    public SearchHotelMessage(int what, String message, Object obj) {
        super(what, message, obj);
    }
}
