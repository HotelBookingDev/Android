package sf.hotel.com.data.interfaceeneity.login;

import android.content.Context;
import android.text.TextUtils;

import rx.Observable;
import rx.Subscriber;
import sf.hotel.com.data.cache.UserCacheImpl;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.CodeException;
import sf.hotel.com.data.utils.CheckUtils;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public class ILRCommendImp implements ILRCommend {
    public static final int LOGIN_SMS = 1;
    public static final int REGISTER_SMS = 2;

    @Override
    public void savePhone(Context context, String phone) {
        PreferencesUtils.savePhone(context, phone);
    }

    @Override
    public void saveAvatar(Context context, String url) {
        PreferencesUtils.saveAvatar(context, url);
    }

    @Override
    public void saveLogin(Context context, boolean isLogin) {
        PreferencesUtils.saveLogin(context, isLogin);
    }

    @Override
    public void saveUserId(Context context, long id) {
        PreferencesUtils.saveUserId(context, id);
    }

    @Override
    public void upDateUserInfo(Context context, UserEntity entity) {
        new UserCacheImpl().update(entity, context);
        saveLogin(context, true);
    }

    @Override
    public Observable<NormalResult> postInllation(String deviceType, String phoneNum,
                                                  String invatllationId) {
        return ApiWrapper.getInstance().postIntalltion(deviceType, phoneNum, invatllationId);
    }

    @Override
    public Observable<NormalResult> postIntallation(Intallation intallation) {
        return ApiWrapper.getInstance().postIntallation(intallation);
    }

    @Override
    public Observable<NormalResult> getSmsCode(String phone, int type) {
        return Observable.create(new Observable.OnSubscribe<NormalResult>() {
            @Override
            public void call(Subscriber<? super NormalResult> subscriber) {
                if (TextUtils.isEmpty(phone)) {
                    subscriber.onError(new APIException(CodeException.REGIST_PHONE_NULL));
                } else if (!(CheckUtils.checkPhoneNumber(phone))) {
                    subscriber.onError(new APIException(CodeException.REGIST_PHONE_ERROR));
                } else {
                    //网络请求的东西
                    if (type == LOGIN_SMS) {
                        ApiWrapper.getInstance().doGetSmsCodeByLogin(phone).subscribe(subscriber);
                    } else if (type == REGISTER_SMS) {
                        ApiWrapper.getInstance().doGetSmsCode(phone).subscribe(subscriber);
                    }
                }
            }
        });
    }

}
