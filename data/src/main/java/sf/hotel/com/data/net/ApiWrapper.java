package sf.hotel.com.data.net;

import rx.Observable;
import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.entity.NormalResult;

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
        return mService.callLogin(username, pwd)
                .compose(ApiWrapper.this.<LoginResult>applySchedulers())
                ;

    }


    //注册
    public Observable<NormalResult> doRegister(String phone, String pwd) {
        return mService.callRegister(phone, pwd)
                .compose(this.<NormalResult>applySchedulers())
                ;
    }
}
