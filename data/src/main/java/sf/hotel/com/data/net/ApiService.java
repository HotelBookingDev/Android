package sf.hotel.com.data.net;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import sf.hotel.com.data.entity.HttpResult;
import sf.hotel.com.data.entity.LoginEntity;
import sf.hotel.com.data.entity.UserEntity;

/**
 * Created by FMT on 2016/6/5:14:14
 * EMAILE 1105896230@qq.com.
 */
public interface ApiService {


    @POST(AppUrl.USER_URL)
    Observable<HttpResult<UserEntity>> getUserInfo(@Query("name") String name,
            @Query("password") String password);

    @POST(AppUrl.SEND_CODE)
    void sendCode(@Query("phone") String phone);

}
