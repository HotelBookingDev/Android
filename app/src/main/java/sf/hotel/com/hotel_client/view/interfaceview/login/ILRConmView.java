package sf.hotel.com.hotel_client.view.interfaceview.login;

import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public interface ILRConmView extends BaseView {
    String getUserName();

    String getPassword();

    void startHomeActivity();

    String getIntallationId();
}
