package sf.hotel.com.hotel_client.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.util.LruCache;
import android.view.KeyEvent;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.utils.TToast;
import sf.hotel.com.hotel_client.view.fragment.LoginFragment;
import sf.hotel.com.hotel_client.view.fragment.RegisterFragment;

/**
 * Created by FMT on 2016/6/3:15:51
 * EMAILE 1105896230@qq.com.
 */
public abstract class BaseActivity extends SupportActivity {


    protected String TAG = this.getClass().getSimpleName();

    protected LruCache<Integer, SupportFragment> mFragmentList = new LruCache<>(3);

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


    public void showFragment(Class fragment) {

        SupportFragment mFragment = findFragment(fragment);
        if (mFragment == null) {
            mFragment = getFragmentByKey(fragment);
            start(mFragment);
        } else {
            start(mFragment);
        }

    }

    protected SupportFragment getFragmentByKey(Class fragment){
        try {
            return (SupportFragment) fragment.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
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
}
