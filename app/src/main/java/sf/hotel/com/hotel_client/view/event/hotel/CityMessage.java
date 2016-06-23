package sf.hotel.com.hotel_client.view.event.hotel;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class CityMessage extends Message {
    public static final int SUCCESS = 1;

    public static final int FAILE = 2;

    public static final int ACTIVITY_FINISH_AND_RESULT = 1001;
    public static final int ACTIVITY_FINISH = 1002;

    public CityMessage(int what) {
        super(what);
    }

    public CityMessage(String message, int what) {
        super(message, what);
    }

    public CityMessage(int what, String message, Object obj) {
        super(what, message, obj);
    }
}
