package sf.hotel.com.data.net;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import sf.hotel.com.data.eneity.HttpResult;
import sf.hotel.com.data.eneity.UserEntity;

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
