package sf.hotel.com.hotel_client.view.presenter;

import android.content.Context;

import com.google.gson.Gson;

import sf.hotel.com.data.datasource.OrderDao;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.entity.netresult.person.OrderReuslt;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.callback.CommSubscriber;
import sf.hotel.com.data.utils.LogUtils;

/**
 * Created by "林其望".
 * DATE: 2016:07:22:16:01
 * email:1105896230@qq.com
 */

public class CustomReceiverPresenter {
    public void updateOrder(String order, Context context) {
        Gson gson=new Gson();
        Order order2 = gson.fromJson(order, Order.class);
        OrderDao.update(order2,context);
    }
}
