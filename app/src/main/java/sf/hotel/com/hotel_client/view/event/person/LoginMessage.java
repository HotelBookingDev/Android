package sf.hotel.com.hotel_client.view.event.person;

import java.util.Objects;

import sf.hotel.com.hotel_client.view.event.Message;

/**
 * Created by 林其望
 * data：2016/6/17
 * email: 1105896230@qq.com
 */
public class LoginMessage extends Message {
    public static final int SHOW_REGIST = 1;
    public static final int SHOW_LOGIN = 2;
    public static final int SHOW_MAIN = 3;



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
