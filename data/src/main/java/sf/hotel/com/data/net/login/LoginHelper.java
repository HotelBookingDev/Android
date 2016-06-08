package sf.hotel.com.data.net.login;

import java.util.Map;

import rx.Observable;
import sf.hotel.com.data.entity.LoginEntity;
import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.net.AppUrl;
import sf.hotel.com.data.net.HttpApiHelper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public class LoginHelper extends HttpApiHelper {
    private static LoginHelper mInstance;
    private LoginService mService;

    private LoginHelper() {
        super.init(AppUrl.LOGIN_HOST);
        mService = mRetrofit.create(LoginService.class);
    }

    public static LoginHelper getInstance() {
        if (mInstance == null) {
            synchronized (LoginHelper.class) {
                if (mInstance == null) mInstance = new LoginHelper();
            }
        }
        return mInstance;
    }

    //登入
    public Observable<LoginResult> doLogin(String username, String pwd) {
        Map<String, String> queryMap = defaultQueryMap();

        queryMap.put("phoneNumber", username);
        queryMap.put("password", pwd);

        return mService.callLogin(username, pwd)
                .compose(this.<LoginResult>applySchedulers())
                ;
    }

    public Observable<NormalResult> doRegister(String phone, String pwd) {
        Map<String, String> queryMap = defaultQueryMap();

        queryMap.put("phoneNumber", phone);
        queryMap.put("password", pwd);

        return mService.callRegister(phone , pwd)
                .compose(this.<NormalResult>applySchedulers())
                ;
    }
}
