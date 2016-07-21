package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import java.util.List;

import rx.Observable;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.entity.netresult.NormalResult;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public interface IOrder {
    Observable<List<Order>> getOrder(Context context, int position);


    Observable<List<Order>> detect(Context context, Order order, int position);

    Observable<List<Order>> loadDatas(Context context, int position);
}
