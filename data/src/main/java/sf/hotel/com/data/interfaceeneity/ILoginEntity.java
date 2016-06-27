package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.entity.UserEntity;

/**
 * Created by FMT on 2016/6/3:19:29
 * EMAILE 1105896230@qq.com.
 */
public interface ILoginEntity {
    Observable<LoginResult> login(String username, String password);

    Observable<NormalResult> postInllation(String deviceType, String phoneNum,
            String invatllationId);

    void savePhone(Context context, String phone);

    void savePwd(Context context, String pwd);

    String getAvatar(Context context);

    void saveAvatar(Context context, String url);

    String getPhone(Context context);

    String getPwd(Context context);

    void upDateUserInfo(Context context, UserEntity entity);

    void saveLogin(Context context,boolean isLogin);
}
