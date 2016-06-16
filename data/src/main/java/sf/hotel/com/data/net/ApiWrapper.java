package sf.hotel.com.data.net;

import java.net.HttpURLConnection;

import rx.Observable;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.entity.UserEntity;

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

    /**
     * 登入
     *
     * @param username
     * @param pwd
     * @return
     */
    public Observable<LoginResult> doLogin(String username, String pwd) {
        return mService.callLogin(username, pwd)
                .compose(ApiWrapper.this.<LoginResult>applySchedulers());
    }

    public Observable<NormalResult> postIntalltion(String deviceType, String phoneNum,
            String installlationId) {
        return mService.postInstallation(deviceType, phoneNum, installlationId)
                .compose(this.<NormalResult>applySchedulers());
    }

    /**
     * 注册
     *
     * @param phone
     * @param smsCode
     * @param pwd
     * @return
     */
    public Observable<NormalResult> doRegister(String phone, String smsCode, String pwd) {
        return mService.callRegister(phone, smsCode, pwd)
                .compose(this.<NormalResult>applySchedulers());
    }

    /**
     * 短信验证码
     *
     * @param phone
     * @return
     */
    public Observable<NormalResult> doGetSmsCode(String phone) {
        return mService.callSmsCode(phone).compose(this.<NormalResult>applySchedulers());
    }

    public Observable<NormalResult> postIntallation(Intallation mIntallation) {
        return mService.postIntallation(mIntallation).compose(this.<NormalResult>applySchedulers());
    }
}
