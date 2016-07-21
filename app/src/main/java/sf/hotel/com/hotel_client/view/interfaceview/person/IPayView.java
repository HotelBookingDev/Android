package sf.hotel.com.hotel_client.view.interfaceview.person;

import android.app.Activity;

import sf.hotel.com.hotel_client.view.interfaceview.BaseView;
import sf.hotel.com.hotel_client.view.presenter.person.PayPresenter;

/**
 * Created by "林其望".
 * DATE: 2016:07:20:19:01
 * email:1105896230@qq.com
 */

public interface IPayView extends BaseView{
    void showPayView(PayPresenter payPresenter);

    void dissMissPayView();

    Activity getActivity();
}
