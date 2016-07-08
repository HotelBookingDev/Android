package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.entity.netresult.NormalResult;

/**
 * Created by 林其望
 * data：2016/7/8
 * email: 1105896230@qq.com
 */
public interface IChangePwd {
    Observable<NormalResult> chagngePwd(String phoneNum, String oldpwd, String newPwd);

    String getPhoneNum();

    void savePwd(Context context, String pwd);
}
