package sf.hotel.com.hotel_client.view.presenter;

import android.content.Context;

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
    public void updateOrder(long order, Context context) {
        ApiWrapper.getInstance().getOrderById(order).map(OrderReuslt::getOrder)
                .doOnNext(order1 -> OrderDao.update(order1,context)).
                subscribe(new CommSubscriber<Order>(){
                    @Override
                    public void onError(Throwable e) {
                        LogUtils.logThrowadle(e);
                    }
                });
    }
}
