package sf.hotel.com.hotel_client.view.event.person;

import java.util.Objects;

import sf.hotel.com.hotel_client.view.event.Message;

/**
 * Created by 林其望
 * data：2016/6/22
 * email: 1105896230@qq.com
 */
public class PersonMessage extends Message {

    public static final int ORDER = 100;

    //进入忘记密码的界面
    public static final int FORGORPW = 101;

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
