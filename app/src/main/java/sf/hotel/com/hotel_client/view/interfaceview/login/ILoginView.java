package sf.hotel.com.hotel_client.view.interfaceview.login;

import android.widget.ImageView;

/**
 * Created by FMT on 2016/6/3:18:47
 * EMAILE 1105896230@qq.com.
 */
public interface ILoginView extends ILRConmView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void login();

    void setEditPhone(String phone);

    void setEditPwd(String pwd);

    ImageView getAvatar();
}
