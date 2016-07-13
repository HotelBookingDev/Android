package sf.hotel.com.data.net;

/**
 * Created by FMT on 2016/6/5:14:11
 * email 1105896230@qq.com.
 */
public class AppUrl {

    public static final String API_HOST = "http://agesd.com/";
    public static final String LOGIN_URL = "user/login/";
    public static final String REGISTER_URL = "user/register/";
    public static final String SMS_URL = "ems/member/regist/";
    public static final String INSTALLATIONS_BIND_URL = "user/installation/bind/";
    public static final String INSTALLATION_URL = "user/installation/";
    public static final String HOTELS_URL = "hotel";
    public static final String HOTELS_BOOK_URL = "hotel/book";
    public static final String HOTEL = "hotel";
    public static final String TOKEN_URL = "user/avatar/token";

    public static final String PROVINCES_URL = "province/";

    public static final String ORDER_URL = "customer/orders/";

    public static final String CHANGEPWD_URL = "user/password/";

    public static final String DELETE_URL = "order/customer/";

    public static final String[] NEEDTOKENURL = {
            INSTALLATIONS_BIND_URL, CHANGEPWD_URL, TOKEN_URL, ORDER_URL, DELETE_URL
    };
}
