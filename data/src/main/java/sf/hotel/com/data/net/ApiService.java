package sf.hotel.com.data.net;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import rx.Observable;
import sf.hotel.com.data.eneity.HttpResult;
import sf.hotel.com.data.eneity.UserEntity;

/**
 * Created by FMT on 2016/6/5:14:14
 * EMAILE 1105896230@qq.com.
 */
public interface ApiService {
    @POST(AppUrl.USER_URL)
    Observable<HttpResult<UserEntity>> getUserInfo(@FieldMap Map<String, String> map);
}
