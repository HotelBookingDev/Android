package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;

/**
 * Created by FMT on 2016/6/3:19:29
 * EMAILE 1105896230@qq.com.
 */
public interface ILoginEntity extends ILRCommend{
    Observable<LoginResult> login(String username, String password);

    Observable<NormalResult> postInllation(String deviceType, String phoneNum,
            String invatllationId);


    String getAvatar(Context context);


    String getPhone(Context context);

    String getPwd(Context context);



}
