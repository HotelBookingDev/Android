package sf.hotel.com.data.datasource;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import sf.hotel.com.data.entity.UserEntity;

/**
 * Created by FMT on 2016/6/3:16:46
 * EMAILE 1105896230@qq.com.
 */
public class UserDao {

    public static void add(UserEntity user, Context context) {
        try {
            DatabaseHelper.getHelper(context).getUserDao().createIfNotExists(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(UserEntity user, Context context) {
        try {
            DatabaseHelper.getHelper(context).getUserDao().createOrUpdate(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isCache(long id, Context context) {
        boolean isCache = false;
        UserEntity userEneity = getUserEneity(id, context);
        if (userEneity != null) {
            isCache = true;
        }
        return isCache;
    }

    public static UserEntity getUserEneity(long id, Context context) {
        UserEntity mUserEntity = null;
        try {
            List<UserEntity> mUserEntitys = DatabaseHelper.getHelper(context)
                    .getUserDao()
                    .queryForEq("userId", id);
            if (mUserEntitys != null && mUserEntitys.size() == 1) {
                mUserEntity = mUserEntitys.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mUserEntity;
    }
}
