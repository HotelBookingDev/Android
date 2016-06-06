package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.cache.UserCacheImpl;
import sf.hotel.com.data.eneity.LoginEntity;
import sf.hotel.com.data.eneity.StateEntity;
import sf.hotel.com.data.eneity.UserEntity;
import sf.hotel.com.data.utils.CheckUtils;

/**
 * Created by FMT on 2016/6/3:19:44
 * EMAILE 1105896230@qq.com.
 */
public class LoginEntityImp implements ILoginEntity {
    @Override
    public Observable<StateEntity<UserEntity>> login(String username, String password) {
        return Observable.just(init(username, password)).flatMap(loginEntityStateEntity -> {
            if (CheckUtils.checkPhoneNumber(loginEntityStateEntity.getData().getUsername()) &&
                    !CheckUtils.isTextViewEmpty(loginEntityStateEntity.getData().getPassword())) {
                return Observable.just(new StateEntity<>(-1, new UserEntity()));
            } else {
                //网络请求的东西
                return Observable.just(new StateEntity<>(200, new UserEntity()));
            }
        });
    }

    @Override
    public Observable<StateEntity<UserEntity>> update(StateEntity<UserEntity> mStateEntity,
            Context context) {
        return Observable.just(mStateEntity)
                .doOnNext(
                        userEntityStateEntity -> new UserCacheImpl().update(mStateEntity.getData(),
                                context));
    }

    private StateEntity<LoginEntity> init(String username, String password) {
        LoginEntity loginEntity = new LoginEntity(username, password);
        return new StateEntity<>(loginEntity);
    }
}
