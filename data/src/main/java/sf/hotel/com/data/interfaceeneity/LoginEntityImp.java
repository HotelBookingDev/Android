package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import rx.Subscriber;
import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.entity.StateEntity;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.utils.CheckUtils;

/**
 * Created by FMT on 2016/6/3:19:44
 * EMAILE 1105896230@qq.com.
 */
public class LoginEntityImp implements ILoginEntity {
    @Override
    public Observable<LoginResult> login(String username, String password) {
        return Observable.create(new Observable.OnSubscribe<LoginResult>() {
            @Override
            public void call(Subscriber<? super LoginResult> subscriber) {
                if (!(CheckUtils.checkPhoneNumber(username) &&
                        !CheckUtils.isTextViewEmpty(password))) {
                    subscriber.onError(new APIException("用户名密码格式不正确"));
                } else {
                    //网络请求的东西
                    ApiWrapper.getInstance().doLogin(username, password);
                }
            }
        });
    }

    @Override
    public Observable<StateEntity<UserEntity>> update(StateEntity<UserEntity> mStateEntity,
            Context context) {
        return null;
    }

//    @Override
//    public Observable<StateEntity<UserEntity>> login(String username, String password) {
//        return Observable.just(init(username, password)).flatMap(loginEntityStateEntity -> {
//            if (!(CheckUtils.checkPhoneNumber(loginEntityStateEntity.getData().getUsername()) &&
//                    !CheckUtils.isTextViewEmpty(loginEntityStateEntity.getData().getPassword()))) {
//                return Observable.just(new StateEntity<>(-1, new UserEntity()));
//
//            } else {
//                //网络请求的东西
//                return Observable.just(new StateEntity<>(200, new UserEntity()));
//            }
//        });
//    }
//
//    @Override
//    public Observable<StateEntity<UserEntity>> update(StateEntity<UserEntity> mStateEntity,
//            Context context) {
//        return Observable.just(mStateEntity)
//                .doOnNext(
//                        userEntityStateEntity -> new UserCacheImpl().update(mStateEntity.getData(),
//                                context));
//    }
//
//    private StateEntity<LoginEntity> init(String username, String password) {
//        LoginEntity loginEntity = new LoginEntity(username, password);
//        return new StateEntity<>(loginEntity);
//    }
}
