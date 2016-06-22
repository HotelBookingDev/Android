package sf.hotel.com.hotel_client.view.event.hotel;

import java.util.Objects;

/**
 * Created by 林其望 on 2016/6/22.
 */
public class PersonMessage extends Message {

    public static final int ORDER = 100;

    public PersonMessage(int what) {
        super(what);
    }

    public PersonMessage(String message, int what) {
        super(message, what);
    }

    public PersonMessage(int what, String message, Objects obj) {
        super(what, message, obj);
    }
}
