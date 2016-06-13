package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import rx.Subscriber;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.CodeException;
import sf.hotel.com.data.utils.CheckUtils;
import sf.hotel.com.data.utils.LogUtils;

/**
 * Created by FMT on 2016/6/3:19:44
 * EMAILE 1105896230@qq.com.
 */
public class LoginEntityImp implements ILoginEntity {
    @Override
    public Observable<UserEntity> login(String username, String password) {
        return Observable.create(new Observable.OnSubscribe<UserEntity>() {
            @Override
            public void call(Subscriber<? super UserEntity> subscriber) {
                if (CheckUtils.isTextViewEmpty(username)) {
                    subscriber.onError(new APIException(CodeException.LOGIN_NAME_NULL));
                } else if (!(CheckUtils.checkPhoneNumber(username))) {
                    subscriber.onError(new APIException(CodeException.LOGIN_FORMAT_ERROR));
                } else if (CheckUtils.isTextViewEmpty(password)) {
                    subscriber.onError(new APIException(CodeException.LOGIN_PWD_NULL));
                } else {
                    //网络请求的东西
                    ApiWrapper.getInstance().doLogin(username, password).subscribe(subscriber);
                }
            }
        });
    }

    @Override
    public Observable<NormalResult> postInllation(String deviceType, String phoneNum,
            String invatllationId) {
        LogUtils.d(invatllationId);
        return ApiWrapper.getInstance().postIntalltion(deviceType, phoneNum, invatllationId);
    }
}
