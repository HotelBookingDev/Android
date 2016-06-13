package sf.hotel.com.hotel_client;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.SaveCallback;
import com.tencent.bugly.crashreport.CrashReport;

import rx.Subscriber;
import sf.hotel.com.data.entity.Intallation;
import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.utils.PreferencesUtils;

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
                postIntallationId();
            }
        });
    }

    private void postIntallationId() {
        String installationId = PreferencesUtils.getInstallationId(context);
        //每次登陆判断本地的设备id和初始化的id 是否是同一个不相同就发送一次请求信息
        if (installationId == null ||
                !installationId.equals(
                        AVInstallation.getCurrentInstallation().getInstallationId())) {
            ApiWrapper.getInstance()
                    .postIntallation(new Intallation("android",
                            AVInstallation.getCurrentInstallation().getInstallationId()))
                    .subscribe(new Subscriber<NormalResult>() {
                        @Override
                        public void onCompleted() {
                            PreferencesUtils.saveInstallationId(context, installationId);
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof APIException) {
                                if (((APIException) e).getCode() == Code.INTALLATIONIDISEXIT) {
                                    PreferencesUtils.saveInstallationId(context, installationId);
                                }
                            }
                        }

                        @Override
                        public void onNext(NormalResult normalResult) {

                        }
                    });
        }
    }

    public void initBuglyStatus() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setAppVersion("1.0.1");
        CrashReport.initCrashReport(context, "900033362", true, strategy);
    }
}
