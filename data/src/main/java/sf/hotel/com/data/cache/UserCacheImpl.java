package sf.hotel.com.data.cache;

import android.content.Context;

import javax.inject.Inject;

import rx.Observable;
import sf.hotel.com.data.eneity.UserEntity;
import sf.hotel.com.data.datasource.UserDao;

/**
 * Created by FMT on 2016/6/3:16:29
 * EMAILE 1105896230@qq.com.
 */
public class UserCacheImpl implements UserCache {
    @Inject
    private UserDao mUserDao;

    @Override
    public Observable<UserEntity> get(long userId, Context context) {
        return Observable.just(mUserDao.getUserEneity(userId, context));
    }

    @Override
    public void add(UserEntity userEntity, final Context context) {
        Observable.just(userEntity).subscribe(userEntity1 -> {
            mUserDao.add(userEntity, context);
        });
    }

    @Override
    public boolean isCached(long userId, Context context) {
        return mUserDao.isCache(userId, context);
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
            mUserDao.update(userEntity, context);
        });
    }
}
