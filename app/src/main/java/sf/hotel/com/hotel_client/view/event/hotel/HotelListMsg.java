package sf.hotel.com.hotel_client.view.event.hotel;


/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class HotelListMsg extends Message {
    public final static int SUCCESS = 1;
    public final static int FAILE = 2;

    public HotelListMsg(int what) {
        super(what);
    }

    public HotelListMsg(String message, int what) {
        super(message, what);
    }

    public HotelListMsg(int what, String message, Object obj) {
        super(what, message, obj);
    }
}
