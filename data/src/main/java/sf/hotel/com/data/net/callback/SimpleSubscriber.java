package sf.hotel.com.data.net.callback;

import android.content.Context;

import rx.Subscriber;
import sf.hotel.com.data.utils.LoadingDialogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public abstract class SimpleSubscriber<T> extends Subscriber<T> {

    Context context;

    public SimpleSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onStart() {
        LoadingDialogUtils.showProgress(context, "正在努力加载中...");
        context = null;
    }

    @Override
    public void onCompleted() {
        LoadingDialogUtils.dismissDialog();
        context = null;
    }

    @Override
    public void onError(Throwable e) {
//        if (e instanceof APIException) {
//            APIException exception = (APIException) e;
//            LogUtils.e(exception.getErrorMessage(exception));
//        } else if (e instanceof SocketTimeoutException) {
//            LogUtils.e(String.valueOf(e.getMessage()));
//        } else if (e instanceof ConnectException) {
//            LogUtils.e(String.valueOf(e.getMessage()));
//        } else {
//            LogUtils.e(String.valueOf(e.getMessage()));
//        }
        LoadingDialogUtils.dismissDialog();
        context = null;
    }

    @Override
    public void onNext(T t) {
        LoadingDialogUtils.dismissDialog();
        context = null;
    }
}
