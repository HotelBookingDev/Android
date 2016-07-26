package sf.hotel.com.data.interfaceeneity.login;

import android.content.Context;

import rx.Observable;
import rx.Subscriber;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.CodeException;
import sf.hotel.com.data.utils.CheckUtils;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * Created by FMT on 2016/6/3:19:44
 * EMAILE 1105896230@qq.com.
 */
public class LoginEntityImp extends ILRCommendImp implements ILoginEntity {

    @Override
    public Observable<LoginResult> login(String username, String code) {
        return Observable.create(new Observable.OnSubscribe<LoginResult>() {
            @Override
            public void call(Subscriber<? super LoginResult> subscriber) {
                if (CheckUtils.isTextViewEmpty(username)) {
                    subscriber.onError(new APIException(CodeException.LOGIN_NAME_NULL));
                } else if (!(CheckUtils.checkPhoneNumber(username))) {
                    subscriber.onError(new APIException(CodeException.LOGIN_FORMAT_ERROR));
                } else if ((CheckUtils.checkPhoneNumber(code))) {
                    subscriber.onError(new APIException(CodeException.SMS_CODE_NULL));
                } else {
                    //网络请求的东西
                    ApiWrapper.getInstance().doLogin(username, code).subscribe(subscriber);
                }
            }
        });
    }


    @Override
    public String getPhone(Context context) {
        return PreferencesUtils.getPhone(context);
    }


}
