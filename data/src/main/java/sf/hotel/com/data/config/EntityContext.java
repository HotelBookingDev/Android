package sf.hotel.com.data.config;

import android.content.Context;

import sf.hotel.com.data.entity.UserEntity;

/**
 * Created by 林其望
 * email: 1105896230@qq.com
 */
public class EntityContext {
    private static EntityContext instance;

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        EntityContext.context = context;
    }

    private UserEntity mCurrentUser;

    public void setmCurrentUser(UserEntity mCurrentUser) {
        this.mCurrentUser = mCurrentUser;
    }

    public UserEntity getmCurrentUser() {
        return mCurrentUser;
    }

    public synchronized static EntityContext getInstance() {
        if (instance == null) {
            instance = new EntityContext();
        }
        return instance;
    }
}
