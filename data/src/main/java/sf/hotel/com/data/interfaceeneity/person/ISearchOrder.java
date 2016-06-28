package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import java.util.List;

import rx.Observable;
import sf.hotel.com.data.entity.LocalOrder;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
public interface ISearchOrder {
    Observable<List<LocalOrder>> getOrders(Context context);

    void search(String OrdersNum);

    void update(String query, Context context);
}