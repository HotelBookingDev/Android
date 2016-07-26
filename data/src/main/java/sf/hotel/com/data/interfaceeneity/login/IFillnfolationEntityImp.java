package sf.hotel.com.data.interfaceeneity.login;

import android.content.Context;

import rx.Observable;
import rx.functions.Func1;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.datasource.UserDao;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * Created by "林其望".
 * DATE: 2016:07:25:18:07
 * email:1105896230@qq.com
 */

public class IFillnfolationEntityImp implements IFillnfolationEntity {
    @Override
    public Observable<UserEntity> submit(String name, String payPwd, String ConfigPayPwd, int sex) {
        return ApiWrapper.getInstance().putUserInfo(sex, name).map(LoginResult::getUserEntity);
    }

    @Override
    public void saveUserEntity(UserEntity userEntity, Context context) {
        UserDao.update(userEntity, context);
        EntityContext.getInstance().setmCurrentUser(userEntity);
    }
}
