package sf.hotel.com.data.interfaceeneity.login;

import android.text.TextUtils;

import rx.Observable;
import rx.Subscriber;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.CodeException;
import sf.hotel.com.data.utils.CheckUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/8.
 */
public class RegisterEntityImp extends ILRCommendImp implements IRegisterEntity {

    @Override
    public Observable<LoginResult> register(String phone, String smsCode) {
        return Observable.create(new Observable.OnSubscribe<LoginResult>() {
            @Override
            public void call(Subscriber<? super LoginResult> subscriber) {
                if (TextUtils.isEmpty(phone)) {
                    subscriber.onError(new APIException(CodeException.REGIST_PHONE_NULL));
                } else if (!(CheckUtils.checkPhoneNumber(phone))) {
                    subscriber.onError(new APIException(CodeException.REGIST_PHONE_ERROR));
                } else if (TextUtils.isEmpty(smsCode)) {
                    subscriber.onError(new APIException(CodeException.SMS_CODE_NULL));
                } else {
                    //网络请求的东西
                    ApiWrapper.getInstance().doRegister(phone, smsCode).subscribe(subscriber);
                }
            }
        });
    }


}
