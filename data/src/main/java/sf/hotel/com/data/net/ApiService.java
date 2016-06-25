package sf.hotel.com.data.net;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import sf.hotel.com.data.entity.Intallation;

import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.entity.netresult.HttpResult;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.entity.netresult.TokenResult;


import sf.hotel.com.data.entity.ProcincesResult;


import static sf.hotel.com.data.net.HttpParam.CITY_ID;
import static sf.hotel.com.data.net.HttpParam.DEVICE_TYPE;
import static sf.hotel.com.data.net.HttpParam.INSTALLATION_CODE;
import static sf.hotel.com.data.net.HttpParam.PASSWORD;
import static sf.hotel.com.data.net.HttpParam.PHONE_NUMBER;
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
    Observable<HttpResult<NormalResult>> callRegister(@Field(PHONE_NUMBER) String phone,
            @Field(SMS_CODE) String smsCode, @Field(PASSWORD) String pwd);

    @FormUrlEncoded
    @POST(AppUrl.SMS_URL)
    Observable<HttpResult<NormalResult>> callSmsCode(@Field(PHONE_NUMBER) String phone);

    //绑定
    @FormUrlEncoded
    @POST(AppUrl.INSTALLTION_BIND_URL)
    Observable<HttpResult<NormalResult>> postInstallation(@Field(DEVICE_TYPE) String type,
            @Field(PHONE_NUMBER) String phoneNum,
            @Field(INSTALLATION_CODE) String installation_code);

    //上传设备号
    @POST(AppUrl.INSTALLTION_URL)
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<HttpResult<NormalResult>> postIntallation(@Body Intallation mIntallation);

    @GET(AppUrl.HOTELS_URL)
    Observable<HttpResult<HotelResult>> callHotelsByCityId(@Query(CITY_ID) String cityId);


    //获取TOKEN
    @GET(AppUrl.TOKEN_URL)
    Observable<HttpResult<TokenResult>> getTokenResult();

    @GET(AppUrl.PROVINCES_URL)
    Observable<HttpResult<ProcincesResult>> callCityList();
}
