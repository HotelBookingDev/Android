package sf.hotel.com.data.interfaceeneity.login;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.entity.netresult.NormalResult;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public interface ISplashEntity extends ILoginEntity {
    void saveCityCode(Context context, String code);

    void saveCityName(Context context, String cityname);

    Observable<NormalResult> checkToken(String phone, String token);

    UserEntity initUserEntity(Context context);
}
