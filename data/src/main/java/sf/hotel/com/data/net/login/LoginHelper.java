package sf.hotel.com.data.net.login;

import java.util.Map;

import rx.Observable;
import sf.hotel.com.data.entity.LoginEntity;
import sf.hotel.com.data.net.HttpApiHelper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public class LoginHelper extends HttpApiHelper {
    private static LoginHelper mInstance;
    private static String LOGIN_API_HOST = "";
    private LoginService mService;

    private LoginHelper(){
        super.init(LOGIN_API_HOST);
        mService =  mRetrofit.create(LoginService.class);
    }

    public static HttpApiHelper getInstance(){
        if (mInstance == null){
            synchronized (HttpApiHelper.class){
                if (mInstance == null) mInstance = new LoginHelper();
            }
        }
        return mInstance;
    }

    //登入
    public Observable<LoginEntity> doLogin(String username, String pwd){
        Map<String, String> queryMap = defaultQueryMap();

        queryMap.put("username", username);
        queryMap.put("password", pwd);

        return mService.callLogin(queryMap)
                .compose(this.<LoginEntity>applySchedulers())
                ;
    }
}
