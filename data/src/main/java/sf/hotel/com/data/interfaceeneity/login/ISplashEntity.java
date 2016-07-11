package sf.hotel.com.data.interfaceeneity.login;

import android.content.Context;

/**
 * Created by 林其望
 * data：2016/7/7
 * email: 1105896230@qq.com
 */
public interface ISplashEntity extends ILoginEntity {
    void saveCityCode(Context context, String code);

    void saveCityName(Context context, String cityname);

    String getUserName(Context context);
}
