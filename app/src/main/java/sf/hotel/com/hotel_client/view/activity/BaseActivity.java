package sf.hotel.com.hotel_client.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.utils.TToast;


/**
 * Created by FMT on 2016/6/3:15:51
 * EMAILE 1105896230@qq.com.
 */
public class BaseActivity extends Activity {
    protected String TAG = this.getClass().getSimpleName();

    protected void showToast(String msg) {
        TToast.showToast(msg);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void showLog(String msg) {
        LogUtils.e(TAG, msg);
    }
}
