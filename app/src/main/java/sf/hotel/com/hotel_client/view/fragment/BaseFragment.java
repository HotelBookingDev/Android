package sf.hotel.com.hotel_client.view.fragment;

import me.yokeyword.fragmentation.SupportFragment;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.utils.TToast;

/**
 * Created by FMT on 2016/6/6:10:09
 * EMAILE 1105896230@qq.com.
 */
public class BaseFragment extends SupportFragment {
    protected String TAG = this.getClass().getSimpleName();
    protected StackClickListener mStackClickListener;

    public void setStackClickListener(StackClickListener mStackClickListener) {
        this.mStackClickListener = mStackClickListener;
    }

    protected void showToast(String msg) {
        TToast.showToast(msg);
    }

    protected void showLog(String msg) {
        LogUtils.e(TAG, msg);
    }
}
