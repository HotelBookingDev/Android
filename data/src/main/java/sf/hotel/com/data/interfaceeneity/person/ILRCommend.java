package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import sf.hotel.com.data.entity.UserEntity;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
//登录注册共同的方法
public interface ILRCommend {
    //    保存手机号
    void savePhone(Context context, String phone);

    //    保存密码
    void savePwd(Context context, String pwd);

    //保存头像
    void saveAvatar(Context context, String url);

    //保存是否是登录状态
    void saveLogin(Context context, boolean isLogin);

    //保存用户id
    void saveUserId(Context context, String id);

    //    更新用户资料和保存用户信息
    void upDateUserInfo(Context context, UserEntity entity);
}
