package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import rx.Subscriber;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.utils.CheckUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/8.
 */
public class RegisterEntityImp implements IRegisterEntity {

    @Override
    public Observable<NormalResult> register(String phone, String smsCode, String pwd) {
        return Observable.create(new Observable.OnSubscribe<NormalResult>() {
            @Override
            public void call(Subscriber<? super NormalResult> subscriber) {
                if (!(CheckUtils.checkPhoneNumber(phone) &&
                    !CheckUtils.isTextViewEmpty(smsCode) &&
                    !CheckUtils.isTextViewEmpty(pwd))) {
                    subscriber.onError(new APIException("用户名密码格式不正确"));
                } else {
                    //网络请求的东西
                    ApiWrapper.getInstance().doRegister(phone, smsCode, pwd);
                }
            }
        });
    }

    @Override
    public Observable<NormalResult> getSmsCode(String phone) {
        return Observable.create(new Observable.OnSubscribe<NormalResult>() {
            @Override
            public void call(Subscriber<? super NormalResult> subscriber) {
                if (!(CheckUtils.checkPhoneNumber(phone))) {
                    subscriber.onError(new APIException("用户名密码格式不正确"));
                } else {
                    //网络请求的东西
                    ApiWrapper.getInstance().doGetSmsCode(phone);
                }
            }
        });
    }
}
