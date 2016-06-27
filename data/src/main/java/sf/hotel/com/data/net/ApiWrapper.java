package sf.hotel.com.data.net;

import android.util.Log;

import com.google.gson.Gson;

import rx.Observable;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.entity.netresult.TokenResult;
import sf.hotel.com.data.utils.LogUtils;

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
        Gson gson = new Gson();
        String s = gson.toJson(mIntallation);
        LogUtils.d("s",s);
        return mService.postIntallation(mIntallation).compose(this.<NormalResult>applySchedulers());
    }

    /**
     * 获取酒店信息通过cityId
     */

    public Observable<HotelResult> callHotelsByCityId(String cityId) {
        return mService.callHotelsByCityId(cityId).compose(this.<HotelResult>applySchedulers());
    }

    public Observable<TokenResult> getTokenResult() {
        return mService.getTokenResult().compose(this.<TokenResult>applySchedulers());
    }

    public Observable<ProcincesResult> callCityList() {
        return mService.callCityList().compose(this.<ProcincesResult>applySchedulers());
    }
}
