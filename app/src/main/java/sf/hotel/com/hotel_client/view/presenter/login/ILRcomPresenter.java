package sf.hotel.com.hotel_client.view.presenter.login;

import android.content.Context;

import sf.hotel.com.data.interfaceeneity.person.ILRCommend;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public class ILRcomPresenter extends SuperPresenter {
    @Override
    public void destroy() {

    }

    protected void saveUserInfo(ILRCommend commend, Context context, String phone, String pwd,
            String avatar, String id) {
        commend.savePhone(context, phone);
        commend.savePwd(context, pwd);
        commend.saveAvatar(context, avatar);
        commend.saveUserId(context, id);
    }
}
