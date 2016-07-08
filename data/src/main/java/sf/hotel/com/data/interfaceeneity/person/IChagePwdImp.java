package sf.hotel.com.data.interfaceeneity.person;

import android.content.Context;

import rx.Observable;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * Created by 林其望
 * data：2016/7/8
 * email: 1105896230@qq.com
 */
public class IChagePwdImp implements IChangePwd {
    @Override
    public Observable<NormalResult> chagngePwd(String phoneNum, String oldpwd, String newPwd) {
        return ApiWrapper.getInstance().putChangePwd(phoneNum, oldpwd, newPwd);
    }

    @Override
    public String getPhoneNum() {
        return EntityContext.getInstance().getmCurrentUser().getPhoneNumber() + "";
    }

    @Override
    public void savePwd(Context context, String pwd) {
        PreferencesUtils.savePhone(context, pwd);
    }
}
