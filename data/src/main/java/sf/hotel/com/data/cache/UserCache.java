package sf.hotel.com.data.cache;

import android.content.Context;

import javax.inject.Inject;

import rx.Observable;
import sf.hotel.com.data.eneity.UserEntity;

/**
 * Created by FMT on 2016/6/3:16:26
 * EMAILE 1105896230@qq.com.
 */
public interface UserCache {
    Observable<UserEntity> get(final long userId, Context context);

    void add(UserEntity userEntity, Context context);

    boolean isCached(long userId, Context context);

    void update(UserEntity userEntity, Context context);
}
