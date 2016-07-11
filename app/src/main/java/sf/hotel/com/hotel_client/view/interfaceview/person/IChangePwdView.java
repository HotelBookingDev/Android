package sf.hotel.com.hotel_client.view.interfaceview.person;

import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望
 * data：2016/6/25
 * email: 1105896230@qq.com
 */
public interface IChangePwdView extends BaseView {
    String getOldPwd();

    String getNewPwd();

    String getConfimPwd();
}
