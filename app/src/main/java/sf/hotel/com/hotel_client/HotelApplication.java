package sf.hotel.com.hotel_client;

import android.app.Application;
import android.content.Context;

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

        //初始化bugly
        initBuglyStatus();
    }

    public void initBuglyStatus() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setAppVersion("1.0.1");
        CrashReport.initCrashReport(context, "900033362", true, strategy);
    }
}
