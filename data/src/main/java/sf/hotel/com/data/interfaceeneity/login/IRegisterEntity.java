package sf.hotel.com.data.interfaceeneity.login;

import rx.Observable;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/8.
 */
public interface IRegisterEntity extends ILRCommend {
    Observable<LoginResult> register(String phone, String smsCode, String pwd);

    Observable<NormalResult> getSmsCode(String phone);
}
