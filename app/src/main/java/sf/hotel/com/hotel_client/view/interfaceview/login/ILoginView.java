package sf.hotel.com.hotel_client.view.interfaceview.login;

import android.widget.EditText;
import android.widget.ImageView;

import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by FMT on 2016/6/3:18:47
 * EMAILE 1105896230@qq.com.
 */
public interface ILoginView extends BaseView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void showFailedError();

    void login();

    EditText getEditName();

    String getIntallationId();

    void startHomeActivity();

    void setEditPhone(String phone);

    void setEditPwd(String pwd);

    ImageView getAvatar();
}
