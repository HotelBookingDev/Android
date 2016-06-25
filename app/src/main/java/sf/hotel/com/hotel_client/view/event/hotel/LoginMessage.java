package sf.hotel.com.hotel_client.view.event.hotel;

import java.util.Objects;

/**
 * Created by 林其望
 * data：2016/6/17
 * email: 1105896230@qq.com
 */
public class LoginMessage extends Message {
    public static final int SHOW_REGIST = 1;
    public static final int SHOW_LOGIN = 2;
    public static final int SHOW_MAIN = 3;


    public final static int FRAGMENT_BACK = 10;

    public LoginMessage(int what) {
        super(what);
    }

    public LoginMessage(String message, int what) {
        super(message, what);
    }

    public LoginMessage(int what, String message, Objects obj) {
        super(what, message, obj);
    }
}
