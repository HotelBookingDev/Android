package sf.hotel.com.hotel_client.view.event.person;

import sf.hotel.com.data.entity.Order;
import sf.hotel.com.hotel_client.view.event.Message;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
public class OrderMessage extends Message {

    public static final int ALREADYCONSUMED = Order.ALRADYORDER;
    public static final int NOTCONSUMED = Order.NOTORDER;
    public static final int PENDING_CONFIRMATION = Order.PENDING_CONFIRMATION;

    public OrderMessage(int what) {
        super(what);
    }

    public OrderMessage(String message, int what) {
        super(message, what);
    }

    public OrderMessage(int what, String message, Object obj) {
        super(what, message, obj);
    }
}
