package sf.hotel.com.data.net.login;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;
import sf.hotel.com.data.entity.HttpResult;
import sf.hotel.com.data.entity.LoginEntity;
import sf.hotel.com.data.net.AppUrl;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public interface LoginService {

    @POST(AppUrl.LOGIN_URL)
    Observable<HttpResult<LoginEntity>> callLogin(@QueryMap Map<String, String> map);
}
