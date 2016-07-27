package sf.hotel.com.data.net;

/**
 * Created by FMT on 2016/6/5:14:11
 * email 1105896230@qq.com.
 */
public class AppUrl {

    public static final String VERSION = "";
    //    public static final String API_HOST = "http://agesd.com/" + VERSION;
    public static final String API_HOST = "http://114.55.67.147/" + VERSION;
    public static final String LOGIN_SMS_URL = "user/sms/login/";
    public static final String CHECK_TOKEN = "api-token-verify/";
    public static final String PROFILE = "user/profile/";
    public static final String LOGIN = "user/login/";
    public static final String REGISTER_URL = "user/register/";
    public static final String SMS_URL = "user/sms/register/";
    public static final String INSTALLATIONS_BIND_URL = "user/installation/bind/";
    public static final String INSTALLATION_URL = "user/installation/";
    public static final String HOTELS_URL = "hotels/";
    public static final String HOTELS_BOOK_URL = "hotel/book";
    public static final String TOKEN_URL = "user/avatar/token/";

    public static final String PROVINCES_URL = "province/";

    public static final String ORDER_URL = "customer/orders/";

    public static final String CHANGEPWD_URL = "user/password/";

    public static final String DELETE_URL = "customer/orders/";

    public static final String PAY_URL = "point/pay/";
    public static final String ROOMPACKAGE = "roompackage/";

    public static final String[] NEEDTOKENURL = {
            INSTALLATIONS_BIND_URL, CHANGEPWD_URL, TOKEN_URL, DELETE_URL, HOTELS_BOOK_URL,PROFILE
    };
    public static final String[] SPEICALURL = {PAY_URL, ORDER_URL, ROOMPACKAGE};
}
