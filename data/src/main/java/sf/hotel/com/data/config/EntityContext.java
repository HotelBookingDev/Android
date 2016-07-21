package sf.hotel.com.data.config;

import android.content.Context;
import android.text.TextUtils;

import rx.Observable;
import sf.hotel.com.data.cache.UserCacheImpl;
import sf.hotel.com.data.entity.UserEntity;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * Created by 林其望
 * email: 1105896230@qq.com
 */
public class EntityContext {
    private static EntityContext instance = new EntityContext();

    private static Context context;

    public static Context getContext() {
        return context;
    }

    //   这里使用的是Application级别的Context不会被回收
    public static void setContext(Context context) {
        EntityContext.context = context;
    }

    private UserEntity mCurrentUser;

    //在登录成功后从后端返回的用户对象
    public void setmCurrentUser(UserEntity mCurrentUser) {
        this.mCurrentUser = mCurrentUser;
    }

    //防止被系统回收,当回收后从数据库拿
    public UserEntity getmCurrentUser() {
        //然后是登录状态和对象被回收的情况去内存中拿，登录状态在每次进入登录界面默认就设置未未登录
        if (mCurrentUser == null && PreferencesUtils.getLogin(context)) {
            getUserByDb().subscribe(userEntity -> {
                mCurrentUser = userEntity;
            });
//            已经登陆，但是记录中标记为未登录，将内存至为null
        } else if (mCurrentUser != null && !PreferencesUtils.getLogin(context)) {
            mCurrentUser = null;
        }
        return mCurrentUser;
    }

    public Observable<UserEntity> getUserByDb() {
        String userid = PreferencesUtils.getUserId(context);
        if (!TextUtils.isEmpty(userid)) {
            Long userId = Long.parseLong(userid);
            return new UserCacheImpl().get(userId, context);
        }
        return Observable.just(null);
    }

    public static EntityContext getInstance() {
        return instance;
    }
}
