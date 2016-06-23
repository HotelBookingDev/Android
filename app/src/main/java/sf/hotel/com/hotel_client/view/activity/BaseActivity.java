package sf.hotel.com.hotel_client.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.view.KeyEvent;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.utils.StatusBarUtil;
import sf.hotel.com.hotel_client.utils.TToast;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

/**
 * Created by FMT on 2016/6/3:15:51
 * EMAILE 1105896230@qq.com.
 */
public abstract class BaseActivity extends SupportActivity {

    CompositeSubscription mCompositeSubscription;

    protected String TAG = this.getClass().getSimpleName();

    protected LruCache<Integer, SupportFragment> mFragmentList = new LruCache<>(3);

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
        StatusBarUtil.setTransparent(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    protected void showLog(String msg) {
        LogUtils.e(TAG, msg);
    }

    protected SupportFragment getFragmentByKey(Class fragment) {

        BaseFragment baseFragment = null;
        try {
            baseFragment = (BaseFragment) fragment.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return baseFragment;
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

    public void showFragment(Class fragment) {

        SupportFragment mFragment = findFragment(fragment);
        if (mFragment == null) {
            mFragment = getFragmentByKey(fragment);
            start(mFragment);
        } else {

            popTo(fragment, true);
//            start(mFragment);
        }
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
}
