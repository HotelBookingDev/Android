package sf.hotel.com.data.net;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import sf.hotel.com.data.entity.HotelBookResult;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.entity.netresult.HttpResult;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.entity.netresult.TokenResult;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomResult;
import sf.hotel.com.data.entity.netresult.pay.PayResult;
import sf.hotel.com.data.entity.netresult.person.OrderResult;

import static sf.hotel.com.data.net.HttpParam.ACTION;
import static sf.hotel.com.data.net.HttpParam.CHECK_IN_TIME;
import static sf.hotel.com.data.net.HttpParam.CHECK_OUT_TIME;
import static sf.hotel.com.data.net.HttpParam.CITY_ID;
import static sf.hotel.com.data.net.HttpParam.DEVICE_TYPE;
import static sf.hotel.com.data.net.HttpParam.EXCLUDE;
import static sf.hotel.com.data.net.HttpParam.GUESTS;
import static sf.hotel.com.data.net.HttpParam.INSTALLATION_CODE;
import static sf.hotel.com.data.net.HttpParam.NUMBER;
import static sf.hotel.com.data.net.HttpParam.PAGE;
import static sf.hotel.com.data.net.HttpParam.PASSWORD;
import static sf.hotel.com.data.net.HttpParam.PHONE_NUMBER;
import static sf.hotel.com.data.net.HttpParam.POINT;
import static sf.hotel.com.data.net.HttpParam.PRODUCTID;
import static sf.hotel.com.data.net.HttpParam.SMS_CODE;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST(AppUrl.LOGIN_URL)
    Observable<HttpResult<LoginResult>> callLogin(@Field(PHONE_NUMBER) String phone,
                                                  @Field(PASSWORD) String pwd);

    @FormUrlEncoded
    @POST(AppUrl.REGISTER_URL)
    Observable<HttpResult<LoginResult>> callRegister(@Field(PHONE_NUMBER) String phone,
                                                     @Field(SMS_CODE) String smsCode, @Field(PASSWORD) String pwd);

    @FormUrlEncoded
    @POST(AppUrl.SMS_URL)
    Observable<HttpResult<NormalResult>> callSmsCode(@Field(PHONE_NUMBER) String phone);

    //绑定
    @FormUrlEncoded
    @POST(AppUrl.INSTALLATIONS_BIND_URL)
    Observable<HttpResult<NormalResult>> postInstallation(@Field(DEVICE_TYPE) String type,
                                                          @Field(PHONE_NUMBER) String phoneNum,
                                                          @Field(INSTALLATION_CODE) String installation_code);

    //上传设备号
    @POST(AppUrl.INSTALLATION_URL)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<HttpResult<NormalResult>> postIntallation(@Body Intallation mIntallation);

    @FormUrlEncoded
    @POST(AppUrl.HOTELS_URL)
    Observable<HttpResult<HotelResult>> callHotelsByCityId(@Field(CITY_ID) String cityId,
                                                           @Field(PAGE) String page,
                                                           @Field(CHECK_IN_TIME) String in,
                                                           @Field(CHECK_OUT_TIME) String out,
                                                           @Field(EXCLUDE) String exclude);

    @GET(AppUrl.HOTELS_BOOK_URL)
    Observable<HttpResult<HotelBookResult>> callHotelBook(@Query(PRODUCTID) String productId,
                                                          @Query(CHECK_IN_TIME) String inTime,
                                                          @Query(CHECK_OUT_TIME) String outTime,
                                                          @Query(GUESTS) String guests);

    @GET("hotel/{id}")
    Observable<HttpResult<RoomResult>> callHotelBeanById(@Path("id") String id,
                                                         @Query(CHECK_IN_TIME) String inTime,
                                                         @Query(CHECK_OUT_TIME) String outTime,
                                                         @Query(EXCLUDE) String exclude);

    //获取TOKEN
    @GET(AppUrl.TOKEN_URL)
    Observable<HttpResult<TokenResult>> getTokenResult();

    @GET(AppUrl.PROVINCES_URL)
    Observable<HttpResult<ProvincesResult>> callCityList();

    @GET(AppUrl.ORDER_URL)
    Observable<HttpResult<OrderResult>> getOrders(@Query(HttpParam.PROCESS_SATE) int postion);




    @FormUrlEncoded
    @POST(AppUrl.CHANGEPWD_URL)
    Observable<HttpResult<NormalResult>> putChangePwd(@Field(PHONE_NUMBER) String phoneNum,
                                                      @Field(PASSWORD) String password, @Field(HttpParam.NEWPASSWORD) String newPassword);

    @FormUrlEncoded
    @POST(AppUrl.DELETE_URL)
    Observable<HttpResult<NormalResult>> deleteOrder(@Field(ACTION) String action,
                                                     @Field(NUMBER) long number);


    @GET(AppUrl.PAY_URL)
    Observable<HttpResult<PayResult>> callPay(@Query(POINT) String point);
}
