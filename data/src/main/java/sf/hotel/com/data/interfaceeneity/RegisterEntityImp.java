package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import rx.Subscriber;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.CodeException;
import sf.hotel.com.data.utils.CheckUtils;
import sf.hotel.com.data.utils.PreferencesUtils;

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
                    subscriber.onError(new APIException(CodeException.LOGIN_FORMAT_ERROR));
                } else {
                    //网络请求的东西
                    ApiWrapper.getInstance().doRegister(phone, smsCode, pwd).subscribe(subscriber);
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
                    subscriber.onError(new APIException(CodeException.LOGIN_FORMAT_ERROR));
                } else {
                    //网络请求的东西
                    ApiWrapper.getInstance().doGetSmsCode(phone).subscribe(subscriber);
                }
            }
        });
    }

    @Override
    public void savePhone(Context context, String phone) {
        PreferencesUtils.savePhone(context, phone);
    }

    @Override
    public void savePwd(Context context, String pwd) {
        PreferencesUtils.savePhone(context, pwd);
    }
}
