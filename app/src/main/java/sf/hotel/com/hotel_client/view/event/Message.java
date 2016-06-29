package sf.hotel.com.hotel_client.view.event;

/**
 * Created by 林其望
 * data：2016/6/17
 * email: 1105896230@qq.com
 */
public class Message {

    public int what;
    private String message;
    public Object obj;

    //公用的message带一个exit的属性
    public static final int ISEXIT = -1;

    public Message(int what) {
        this.what = what;
    }

    public Message(String message, int what) {
        this.message = message;
        this.what = what;
    }

    public Message(int what, String message, Object obj) {
        this.what = what;
        this.message = message;
        this.obj = obj;
    }
}
