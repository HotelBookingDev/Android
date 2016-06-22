package sf.hotel.com.hotel_client.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.utils.TToast;

/**
 * Created by FMT on 2016/6/6:10:09
 * EMAILE 1105896230@qq.com.
 */
public class BaseFragment extends SupportFragment {
    protected String TAG = this.getClass().getSimpleName();
    protected CompositeSubscription mCompositeSubscription;

    protected void showToast(String msg) {
        TToast.showToast(msg);
    }

    protected void showLog(String msg) {
        LogUtils.e(TAG, msg);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mCompositeSubscription = new CompositeSubscription();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null) {
            if (mCompositeSubscription.isUnsubscribed()) mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCompositeSubscription != null) {
            if (mCompositeSubscription.isUnsubscribed()) mCompositeSubscription.unsubscribe();
        }
    }

    public void starFragment(Class fragment) {
        start(getFragmentByKey(fragment));
    }

    protected SupportFragment getFragmentByKey(Class fragment) {

        BaseFragment baseFragment = null;
        try {
            baseFragment = (BaseFragment) fragment.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return baseFragment;
    }
}
