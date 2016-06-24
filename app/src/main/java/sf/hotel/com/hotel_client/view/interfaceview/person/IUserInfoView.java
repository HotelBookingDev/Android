package sf.hotel.com.hotel_client.view.interfaceview.person;

import android.content.Context;
import android.widget.ImageView;

import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望 on 2016/6/22.
 */
public interface IUserInfoView extends BaseView {

    ImageView getAvatar();

    Context getBottomContext();
}
