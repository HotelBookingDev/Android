package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.eneity.StateEntity;
import sf.hotel.com.data.eneity.UserEntity;

/**
 * Created by FMT on 2016/6/3:19:29
 * EMAILE 1105896230@qq.com.
 */
public interface ILoginEntity {
    Observable<StateEntity<UserEntity>> login(String username, String password);

    Observable<StateEntity<UserEntity>> update(StateEntity<UserEntity> mStateEntity,
            Context context);
}
