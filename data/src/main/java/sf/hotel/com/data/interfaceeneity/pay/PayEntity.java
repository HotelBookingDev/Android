package sf.hotel.com.data.interfaceeneity.pay;

import rx.Observable;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/19.
 */
public interface PayEntity {
    Observable<String> callPay(String point);
}
