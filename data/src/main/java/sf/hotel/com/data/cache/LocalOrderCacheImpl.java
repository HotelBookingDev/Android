package sf.hotel.com.data.cache;

import android.content.Context;

import java.util.List;

import rx.Observable;
import sf.hotel.com.data.datasource.LocalOrderDao;
import sf.hotel.com.data.entity.LocalOrder;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
public class LocalOrderCacheImpl implements LocalOrderCache {
    private static final int RUTURN_NAME = 5;

    @Override
    public Observable<List<LocalOrder>> get(long userId, Context context) {
        return Observable.just(LocalOrderDao.getLocalOrders(userId, context))
                .filter(localOrders -> localOrders != null && localOrders.size() > 0)
                .map(localOrders -> {
                    if (localOrders.size() > RUTURN_NAME) {
                        localOrders = localOrders.subList(0, RUTURN_NAME - 1);
                    }
                    return localOrders;
                });
    }

    @Override
    public void add(LocalOrder mLocalOrder, Context context) {
        Observable.just(mLocalOrder).subscribe(userEntity1 -> {
            LocalOrderDao.add(mLocalOrder, context);
        });
    }

    @Override
    public void update(LocalOrder mLocalOrder, Context context) {
        Observable.just(mLocalOrder).filter(userEntity1 -> {
            boolean cached = isCache(mLocalOrder.getOrderNum(), context);
            if (!cached) {
                add(mLocalOrder, context);
            }
            return cached;
        }).subscribe(userEntity1 -> {
            LocalOrderDao.update(mLocalOrder, context);
        });
    }

    @Override
    public boolean isCache(String orderId, Context context) {
        return LocalOrderDao.isCache(orderId, context);
    }
}
