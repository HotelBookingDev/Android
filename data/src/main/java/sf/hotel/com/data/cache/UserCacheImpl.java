package sf.hotel.com.data.cache;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.datasource.UserDao;
import sf.hotel.com.data.entity.UserEntity;

/**
 * Created by FMT on 2016/6/3:16:29
 * EMAILE 1105896230@qq.com.
 */
public class UserCacheImpl implements UserCache {

    @Override
    public Observable<UserEntity> get(long phoneNum, Context context) {
        return Observable.just(UserDao.getUserEntity(phoneNum, context));
    }

    @Override
    public void add(UserEntity userEntity, final Context context) {
        Observable.just(userEntity).subscribe(userEntity1 -> {
            UserDao.add(userEntity, context);
        });
    }

    @Override
    public boolean isCached(long userId, Context context) {
        return UserDao.isCache(userId, context);
    }

    @Override
    public void update(UserEntity userEntity, Context context) {
        Observable.just(userEntity).filter(userEntity1 -> {
            boolean cached = isCached(userEntity1.getUserId(), context);
            if (!cached) {
                add(userEntity, context);
            }
            return cached;
        }).subscribe(userEntity1 -> {
            UserDao.update(userEntity, context);
        });
    }
}
