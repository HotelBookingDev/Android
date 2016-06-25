package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import rx.Subscriber;
import sf.hotel.com.data.entity.netresult.TokenResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * Created by 林其望
 * data：2016/6/22
 * email: 1105896230@qq.com
 */
public class IUserInfoEntityImp implements IUserInfoEntity {
    @Override
    public Observable<TokenResult> getToken() {
        return Observable.create(new Observable.OnSubscribe<TokenResult>() {
            @Override
            public void call(Subscriber<? super TokenResult> subscriber) {
                ApiWrapper.getInstance().getTokenResult().subscribe(subscriber);
            }
        });
    }
}
