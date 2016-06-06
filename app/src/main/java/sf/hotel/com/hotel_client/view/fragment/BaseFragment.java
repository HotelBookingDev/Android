package sf.hotel.com.hotel_client.view.fragment;

import android.app.Fragment;

import sf.hotel.com.data.utils.LLog;
import sf.hotel.com.hotel_client.utils.TToast;

/**
 * Created by FMT on 2016/6/6:10:09
 * EMAILE 1105896230@qq.com.
 */
public class BaseFragment extends Fragment {
    protected String TAG = this.getClass().getSimpleName();

    protected void showToast(String msg) {
        TToast.showToast(msg);
    }

    protected void showLog(String msg) {
        LLog.e(TAG, msg);
    }
}
