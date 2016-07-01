package sf.hotel.com.hotel_client.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.baidu.location.BDLocation;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.TToast;
import sf.hotel.com.hotel_client.utils.transulcent.TransulcentUtils;

/**
 * Created by FMT on 2016/6/3:15:51
 * email 1105896230@qq.com.
 */
public abstract class BaseActivity extends SupportActivity {

    public CompositeSubscription mCompositeSubscription;

    protected String TAG = this.getClass().getSimpleName();

    protected void showToast(String msg) {
        TToast.showToast(msg);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mCompositeSubscription = new CompositeSubscription();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TransulcentUtils.setColorWindow(this, R.color.view_title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    protected void showLog(String msg) {
        LogUtils.e(TAG, msg);
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * 检测网络连接
     */
    public boolean checkConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        return isWifiConn || isMobileConn;
    }

    public void showPrompt(int type) {
        if (type == BDLocation.TypeNetWorkException) {
            showToast("当前网络不稳定");
        } else if (type == BDLocation.TypeOffLineLocation) {
            if (!checkConnection(this)) {
                showToast("当前网络状态无");
            } else {
                showToast("定位权限未打开");
            }
        }
    }
}
