package sf.hotel.com.data.net.login;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import sf.hotel.com.data.entity.HttpResult;
import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.net.AppUrl;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public interface LoginService {

    @FormUrlEncoded
    @POST(AppUrl.LOGIN_URL)
    Observable<HttpResult<LoginResult>> callLogin(@Field("phoneNumber") String phone,
                                                  @Field("password") String pwd);

    @FormUrlEncoded
    @POST(AppUrl.REGISTER_URL)
    Observable<HttpResult<NormalResult>> callRegister(@Field("phoneNumber") String phone,
                                                      @Field("password") String pwd);
}
