package sf.hotel.com.data.interfaceeneity.login;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.entity.netresult.NormalResult;

/**
 * Created by "林其望".
 * DATE: 2016:07:25:18:07
 * email:1105896230@qq.com
 */

public interface IFillnfolationEntity {
    Observable<UserEntity> submit(String name, String payPwd, String ConfigPayPwd, int sex);

    void saveUserEntity(UserEntity userEntity, Context context);
}
