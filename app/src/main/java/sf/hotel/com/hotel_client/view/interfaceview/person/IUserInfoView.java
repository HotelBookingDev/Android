package sf.hotel.com.hotel_client.view.interfaceview.person;

import android.widget.ImageView;

import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望
 * data：2016/6/22
 * email: 1105896230@qq.com
 */
public interface IUserInfoView extends BaseView {

    ImageView getAvatar();

    void setUserName(String name);

    void setUserPwd(String pwd);
}
