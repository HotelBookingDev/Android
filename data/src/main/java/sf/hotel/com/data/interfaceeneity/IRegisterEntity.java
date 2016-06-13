package sf.hotel.com.data.interfaceeneity;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.entity.NormalResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/8.
 */
public interface IRegisterEntity {
    Observable<NormalResult> register(String phone, String smsCode, String pwd);

    Observable<NormalResult> getSmsCode(String phone);

    void savePhone(Context context, String phone);

    void savePwd(Context context, String pwd);
}
