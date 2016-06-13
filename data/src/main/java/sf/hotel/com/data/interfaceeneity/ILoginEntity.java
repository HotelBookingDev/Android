package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.entity.UserEntity;

/**
 * Created by FMT on 2016/6/3:19:29
 * EMAILE 1105896230@qq.com.
 */
public interface ILoginEntity {
    Observable<UserEntity> login(String username, String password);

    Observable<NormalResult> postInllation(String deviceType, String phoneNum,
            String invatllationId);

    void savePhone(Context context, String phone);

    void savePwd(Context context, String pwd);

    String getPhone(Context context);

    String getPwd(Context context);
}
