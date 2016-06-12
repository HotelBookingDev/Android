package sf.hotel.com.data.net;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import sf.hotel.com.data.entity.HttpResult;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.entity.UserEntity;

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
    Observable<HttpResult<UserEntity>> callLogin(@Field(PHONE_NUMBER) String phone,
            @Field(PASSWORD) String pwd);

    @FormUrlEncoded
    @POST(AppUrl.REGISTER_URL)
    Observable<HttpResult<NormalResult>> callRegister(@Field(PHONE_NUMBER) String phone,
            @Field(SMS_CODE) String smsCode, @Field(PASSWORD) String pwd);

    @FormUrlEncoded
    @POST(AppUrl.SMS_URL)
    Observable<HttpResult<NormalResult>> callSmsCode(@Field(PHONE_NUMBER) String phone);

    @FormUrlEncoded
    @POST(AppUrl.INSTALLTION_BIND_URL)
    Observable<HttpResult<NormalResult>> postInstallation(@Field(DEVICE_TYPE) String type,
            @Field(PHONE_NUMBER) String phoneNumm,
            @Field(INSTALLATION_CODE) String installation_code);
}
