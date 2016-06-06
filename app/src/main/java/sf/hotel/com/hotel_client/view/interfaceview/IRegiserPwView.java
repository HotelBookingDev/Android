package sf.hotel.com.hotel_client.view.interfaceview;

/**
 * Created by FMT on 2016/6/6:10:13
 * EMAILE 1105896230@qq.com.
 */
public interface IRegiserPwView extends BaseView{
    String getPhoneNum();

    void sendInvitationCode();

    void setInvitationCodeNum(String num);

    void reset();
}
