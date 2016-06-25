package sf.hotel.com.hotel_client.view.interfaceview.person;

import android.widget.EditText;

import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望 on 2016/6/25.
 */
public interface IChangePwdView extends BaseView{
    EditText getOldPwd();

    EditText getNewPwd();

    EditText getConfimPwd();

}
