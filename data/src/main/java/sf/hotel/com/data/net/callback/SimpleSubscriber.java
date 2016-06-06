package sf.hotel.com.data.net.callback;

import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;
import rx.functions.Action1;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.utils.LoadingDialogUtils;
import sf.hotel.com.data.utils.LogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/6.
 */
public class SimpleSubscriber<T> extends Subscriber<T> {

    Context context;
    Action1<T> action;

    public SimpleSubscriber(Context context, Action1<T> action){
        this.context = context;
        this.action = action;
    }
    
    @Override public void onStart(){
        LoadingDialogUtils.showProgress(context, "正在努力加载中...");
    }

    @Override
    public void onCompleted() {
        LoadingDialogUtils.dismissDialog();
    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof APIException) {
            APIException exception = (APIException) e;
            LogUtils.e(exception.getErrorMessage(exception));
        } else if (e instanceof SocketTimeoutException) {
            LogUtils.e(String.valueOf(e.getMessage()));
        } else if (e instanceof ConnectException) {
            LogUtils.e(String.valueOf(e.getMessage()));
        } else {
            LogUtils.e(String.valueOf(e.getMessage()));
        }
        LoadingDialogUtils.dismissDialog();
    }

    @Override
    public void onNext(T t) {
        action.call(t);
        LoadingDialogUtils.dismissDialog();
    }
}
