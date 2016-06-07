package sf.hotel.com.hotel_client.view.interfaceview;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public interface IRegisterView extends BaseView{


    public void register();

    public void callPhoneCaptcha();

    public String getUName();

    public String getPwd();

    public String getCaptcha();


}
