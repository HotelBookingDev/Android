package sf.hotel.com.hotel_client.view.interfaceview.person;

import sf.hotel.com.hotel_client.view.custom.ToggleButton;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望
 * data：2016/6/24
 * email: 1105896230@qq.com
 */
public interface ISettingView extends BaseView {
    void setSwitch(boolean isOpen, ToggleButton button);

    ToggleButton getAcceptMsg();

    void loginOut();

    void logOutToLoginActivity();

    void toChangePwFragment();

    void showLoginActivity();
}
