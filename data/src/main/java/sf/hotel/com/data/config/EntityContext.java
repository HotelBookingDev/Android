package sf.hotel.com.data.config;

import sf.hotel.com.data.entity.UserEntity;

/**
 * Created by 林其望 on 2016/6/16.
 */
public class EntityContext {
    private static EntityContext instance;

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
