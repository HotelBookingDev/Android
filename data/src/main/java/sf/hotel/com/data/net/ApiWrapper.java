package sf.hotel.com.data.net;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.entity.StateEntity;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.utils.CheckUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public class ApiWrapper extends RetrofitHelper {
    private static ApiWrapper mInstance;
    private ApiService mService;

    private ApiWrapper() {
        super.init(AppUrl.API_HOST);
        mService = mRetrofit.create(ApiService.class);
    }

    public static ApiWrapper getInstance() {
        if (mInstance == null) {
            synchronized (ApiWrapper.class) {
                if (mInstance == null) mInstance = new ApiWrapper();
            }
        }
        return mInstance;
    }

    //登入
    public Observable<LoginResult> doLogin(String username, String pwd) {
       return Observable.create(new Observable.OnSubscribe<LoginResult>(){
           @Override
           public void call(Subscriber<? super LoginResult> subscriber) {
               if (!(CheckUtils.checkPhoneNumber(username) &&
                       !CheckUtils.isTextViewEmpty(pwd))){
                   subscriber.onError(new APIException("用户名密码格式不正确"));
               } else {
                   //网络请求的东西
                   mService.callLogin(username, pwd)
                           .compose(ApiWrapper.this.<LoginResult>applySchedulers())
                   ;
               }
           }
       });
    }



    //注册
    public Observable<NormalResult> doRegister(String phone, String pwd) {
        return mService.callRegister(phone , pwd)
                .compose(this.<NormalResult>applySchedulers())
                ;
    }
}
