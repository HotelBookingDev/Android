package sf.hotel.com.hotel_client;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.SaveCallback;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by FMT on 2016/6/3:19:04
 * EMAILE 1105896230@qq.com.
 */
public class HotelApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        initCloud();
        //初始化bugly
        initBuglyStatus();
    }

    private void initCloud() {
        //初始化
        AVOSCloud.initialize(this, "P0fN7ArvLMtcgsACRwhOupHj-gzGzoHsz", "cWK8NHllNg7N6huHiKA1HeRG");

        //获取设备号
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {

            }
        });
    }

    public void initBuglyStatus() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setAppVersion("1.0.1");
        CrashReport.initCrashReport(context, "900033362", true, strategy);
    }
}
