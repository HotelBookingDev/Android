package sf.hotel.com.hotel_client.view.activity;


import sf.hotel.com.hotel_client.view.fragment.hotel.DetailFragment;
import sf.hotel.com.hotel_client.view.fragment.hotel.HotelsFragment;
import sf.hotel.com.hotel_client.view.fragment.hotel.RoomFragment;
import sf.hotel.com.hotel_client.view.fragment.login.LoginFragment;
import sf.hotel.com.hotel_client.view.fragment.login.RegisterFragment;
import sf.hotel.com.hotel_client.view.fragment.person.OrderFragment;
import sf.hotel.com.hotel_client.view.fragment.person.PersonFragment;
import sf.hotel.com.hotel_client.view.fragment.person.UserInfoFragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/14.
 */
public final class FragConstant {
    public static Class LOGIN = LoginFragment.class;
    public static Class REGISTER = RegisterFragment.class;

    public final static Class HOTELS = HotelsFragment.class;
    public final static Class DETAIL = DetailFragment.class;

    public final static Class ROOM = RoomFragment.class;

    public final static Class PERSON = PersonFragment.class;

    public final static Class ORDER = OrderFragment.class;
    public final static Class USERINFO = UserInfoFragment.class;
}
