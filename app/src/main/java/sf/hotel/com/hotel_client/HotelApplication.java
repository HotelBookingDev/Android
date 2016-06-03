package sf.hotel.com.hotel_client;

import android.app.Application;
import android.content.Context;

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
    }
}
