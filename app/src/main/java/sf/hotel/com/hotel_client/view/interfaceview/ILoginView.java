package sf.hotel.com.hotel_client.view.interfaceview;

import android.widget.EditText;

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
}
