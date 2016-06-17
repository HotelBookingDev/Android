package sf.hotel.com.hotel_client.view.event;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/16.
 */
public class RxBus  {
    private static volatile RxBus defaultInstance;
    private final SerializedSubject<Object, Object> subject;

    private RxBus() {
        subject = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    public void post(Object object) {
        subject.onNext(object);
    }

    public  <T> Observable<T> toObservable(final Class<T> type) {
        return subject.ofType(type);
    }

    public boolean hasObservers() {
        return subject.hasObservers();
    }
}
