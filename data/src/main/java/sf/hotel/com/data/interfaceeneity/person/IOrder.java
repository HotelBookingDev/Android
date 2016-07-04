package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import java.util.List;

import rx.Observable;
import sf.hotel.com.data.entity.Order;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public interface IOrder {
    Observable<List<Order>> getOrderByDb(Context context, int position);
}
