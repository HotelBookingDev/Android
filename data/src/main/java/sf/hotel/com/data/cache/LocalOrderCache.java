package sf.hotel.com.data.cache;

import android.content.Context;

import java.util.List;

import rx.Observable;
import sf.hotel.com.data.entity.LocalOrder;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
public interface LocalOrderCache {
    Observable<List<LocalOrder>> get(final long userId, Context context);

    void add(LocalOrder mLocalOrder, Context context);

    void update(LocalOrder mLocalOrder, Context context);

    boolean isCache(String orderId, Context context);
}
