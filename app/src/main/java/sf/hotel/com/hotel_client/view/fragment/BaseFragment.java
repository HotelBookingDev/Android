package sf.hotel.com.hotel_client.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.utils.AndroidUtils;
import sf.hotel.com.hotel_client.utils.TToast;

/**
 * Created by FMT on 2016/6/6:10:09
 * EMAILE 1105896230@qq.com.
 */
public class BaseFragment extends SupportFragment {
    protected String TAG = this.getClass().getSimpleName();
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeSubscription = new CompositeSubscription();
    }

    public void addSubscription(Subscription subscription) {
        mCompositeSubscription.add(subscription);
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
    }

    public Context getBottomContext() {
        return getActivity();
    }

    public void showViewToast(String msg) {
        TToast.showToast(msg);
    }

    protected void showLog(String msg) {
        LogUtils.e(TAG, msg);
    }

    public String getIntallationId() {
        return AndroidUtils.getInstallationId();
    }
}
