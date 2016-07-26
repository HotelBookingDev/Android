package sf.hotel.com.data.interfaceeneity.login;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.datasource.UserDao;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public class ISplahImp extends LoginEntityImp implements ISplashEntity {
    @Override
    public void saveCityCode(Context context, String code) {
        PreferencesUtils.saveCityCode(context, code);
    }

    @Override
    public void saveCityName(Context context, String cityname) {
        PreferencesUtils.saveCityName(context, cityname);
    }

    @Override
    public Observable<NormalResult> checkToken(String phone, String token) {
        return ApiWrapper.getInstance().checkToken(phone, token);
    }

    @Override
    public UserEntity initUserEntity(Context context) {
        long userId = PreferencesUtils.getUserId(context);
        UserEntity entity = null;
        if (userId != -1) {
            entity = UserDao.getUserEntity(userId, context);
            PreferencesUtils.saveLogin(context,true);
            EntityContext.getInstance().setmCurrentUser(entity);
        }
        return entity;
    }
}
