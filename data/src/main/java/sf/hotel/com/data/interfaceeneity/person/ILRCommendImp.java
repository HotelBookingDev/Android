package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import sf.hotel.com.data.cache.UserCacheImpl;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public class ILRCommendImp implements ILRCommend {
    @Override
    public void savePhone(Context context, String phone) {
        PreferencesUtils.savePhone(context, phone);
    }

    @Override
    public void savePwd(Context context, String pwd) {
        PreferencesUtils.savePassWord(context, pwd);
    }

    @Override
    public void saveAvatar(Context context, String url) {
        PreferencesUtils.saveAvatar(context, url);
    }

    @Override
    public void saveLogin(Context context, boolean isLogin) {
        PreferencesUtils.saveLogin(context, isLogin);
    }

    @Override
    public void saveUserId(Context context, String id) {
        PreferencesUtils.saveUserId(context, id);
    }

    @Override
    public void upDateUserInfo(Context context, UserEntity entity) {
        new UserCacheImpl().update(entity, context);
        saveLogin(context, true);
    }
}
