package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import java.util.List;

import rx.Observable;
import sf.hotel.com.data.cache.LocalOrderCacheImpl;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.entity.LocalOrder;
import sf.hotel.com.data.utils.TimeUtils;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
public class ISearchOrderImp implements ISearchOrder {

    //避免次都都从数据库查找
    private List<LocalOrder> mList = null;

    @Override
    public Observable<List<LocalOrder>> getOrders(Context context) {
        Observable<List<LocalOrder>> mObservable = null;
        if (mList != null) {
            mObservable = Observable.just(mList);
        } else {
            mObservable = new LocalOrderCacheImpl().get(
                    EntityContext.getInstance().getmCurrentUser().getUserId(), context);
        }
        return mObservable;
    }

    @Override
    public void search(String OrdersNum) {

    }

    @Override
    public void update(String query, Context context) {
        LocalOrder localOrder = new LocalOrder();
        localOrder.setOrderNum(query);
        localOrder.setUpdate_time(TimeUtils.getCurrentTimes());
        localOrder.setUserId(
                String.valueOf(EntityContext.getInstance().getmCurrentUser().getUserId()));
        new LocalOrderCacheImpl().update(localOrder, context);
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
