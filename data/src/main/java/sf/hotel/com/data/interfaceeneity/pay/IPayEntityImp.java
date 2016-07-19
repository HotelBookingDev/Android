package sf.hotel.com.data.interfaceeneity.pay;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.utils.LogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/19.
 */
public class IPayEntityImp implements PayEntity {

    public Observable<String> callPay(String point){
        return ApiWrapper.getInstance().callPay(point).flatMap(new Func1<ResponseBody, Observable<String>>() {
            @Override
            public Observable<String> call(ResponseBody responseBody) {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            String s = responseBody.string();
                            subscriber.onNext(s);
                            if (subscriber.isUnsubscribed()){
                                subscriber.onCompleted();
                            }
                        } catch (IOException e) {
                            LogUtils.printExceptionStackTrace(e);
                            subscriber.onError(e);
                        }
                    }
                });
            }
        });
    }
}
