package sf.hotel.com.hotel_client.view.interfaceview;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/8.
 */
public interface ICallBack  {
    int LOGIN = 1;
    int REGISTER = 2;
    int SMS_CODE = 3;
    void success(int type);
    void failed(int type, Throwable e);
}
