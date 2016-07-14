package sf.hotel.com.hotel_client.view.interfaceview.person;

import java.util.List;

import sf.hotel.com.data.entity.Order;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public interface IUserOrderView extends BaseView {
    void showOrder(List<Order> mOrders);

    int getPosition();

    void pullViewComplete();
}
