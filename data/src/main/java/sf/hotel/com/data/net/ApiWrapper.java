package sf.hotel.com.data.net;

import com.google.gson.Gson;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.entity.netresult.HttpResult;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.entity.netresult.TokenResult;
import sf.hotel.com.data.entity.netresult.hotel.Hotel1Result;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomResult;
import sf.hotel.com.data.entity.netresult.pay.PayResult;
import sf.hotel.com.data.entity.netresult.person.OrderManagerResult;
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
    public Observable<LoginResult> doRegister(String phone, String smsCode, String pwd) {
        return mService.callRegister(phone, smsCode, pwd)
                .compose(this.<LoginResult>applySchedulers());
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
        LogUtils.d("sr", s);
        return mService.postIntallation(mIntallation).compose(this.<NormalResult>applySchedulers());
    }

    /**
     * @param cityId 城市ID
     * @param page 页码
     * @param inTime 开始时间
     * @param outTime 结束时间
     * @param exclude 忽略的酒店
     * @return
     */
    public Observable<HotelResult> callHotelsByCityId(String cityId, String page, String inTime,
            String outTime, String exclude) {
        return mService.callHotelsByCityId(cityId, page, inTime, outTime, exclude)
                .compose(this.<HotelResult>applySchedulers());
    }

    /**
     * @param productId 商品id
     * @param inTime 开始时间
     * @param outTime 结束时间
     *
     * @return
     */
    public Observable<HotelBookResult> callHotelBook(String productId, String inTime,
            String outTime, String guests) {
        return mService.callHotelBook(productId, inTime, outTime, guests)
                .compose(this.<HotelBookResult>applySchedulers());
    }


    /**
     * @param id hotel的id
     * @return
     */
    public Observable<RoomResult> callHotelBean(String id, String inTime, String outTime, String exclude){
        return mService.callHotelBeanById(id, inTime, outTime, exclude)
                .compose(this.<RoomResult>applySchedulers());
    }

    public Observable<PayResult> callPay(String point){
        return mService.callPay(point)
                .compose(this.<PayResult>applySchedulers())
                ;
    }

    public Observable<TokenResult> getTokenResult() {
        return mService.getTokenResult().compose(this.<TokenResult>applySchedulers());
    }

    public Observable<ProvincesResult> callCityList() {
        return mService.callCityList().compose(this.<ProvincesResult>applySchedulers());
    }

    public Observable<OrderManagerResult> getOrderManager() {
        return mService.getOrderManager().compose(this.applySchedulers());
    }

    public Observable<NormalResult> putChangePwd(String phoneNum, String pwd, String newPwd) {
        return mService.putChangePwd(phoneNum, pwd, newPwd).compose(this.applySchedulers());
    }

    public Observable<NormalResult> deleteOrder(long number) {
        return mService.deleteOrder("cancel", number).compose(this.applySchedulers());
    }

    @Override
    protected <T> Observable.Transformer<HttpResult<T>, T> applySchedulers() {
        return super.applySchedulers();
    }
}
