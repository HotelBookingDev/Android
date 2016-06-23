package sf.hotel.com.hotel_client;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.SaveCallback;
import com.tencent.bugly.crashreport.CrashReport;

import rx.Subscriber;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.utils.locationoptions.LocationService;

/**
 * Created by FMT on 2016/6/3:19:04
 * EMAILE 1105896230@qq.com.
 */
public class HotelApplication extends Application {
    public static Context context;
    public LocationService locationService;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        //让data可以获取到context
        EntityContext.setContext(context);
        initCloud();
        //初始化bugly
        initBuglyStatus();

        //初始化定位
        locationService = new LocationService(getApplicationContext());
    }

    private void initCloud() {
        //初始化
        AVOSCloud.initialize(this, "P0fN7ArvLMtcgsACRwhOupHj-gzGzoHsz", "cWK8NHllNg7N6huHiKA1HeRG");


    }



    public void initBuglyStatus() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setAppVersion("1.0.1");
        CrashReport.initCrashReport(context, "900033362", true, strategy);
    }
}
