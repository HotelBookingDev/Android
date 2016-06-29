package sf.hotel.com.hotel_client.view.interfaceview.login;

import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public interface IRegisterView extends BaseView {

    void register();

    void callPhoneCaptcha();

    String getUName();

    String getPwd();

    String getCaptcha();

    void startTimer();

    void showLogin();
}
