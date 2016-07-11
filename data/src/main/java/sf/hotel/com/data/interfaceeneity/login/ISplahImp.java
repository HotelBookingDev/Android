package sf.hotel.com.data.interfaceeneity.login;

import android.content.Context;

import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public class ISplahImp extends LoginEntityImp implements ISplashEntity {
    @Override
    public void saveCityCode(Context context,String code) {
        PreferencesUtils.saveCityCode(context,code);
    }

    @Override
    public void saveCityName(Context context,String cityname) {
        PreferencesUtils.saveCityName(context,cityname);
    }

    @Override
    public String getUserName(Context context) {
        return PreferencesUtils.getPhone(context);
    }
}
