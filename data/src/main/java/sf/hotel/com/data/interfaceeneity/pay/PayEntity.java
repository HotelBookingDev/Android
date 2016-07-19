package sf.hotel.com.data.interfaceeneity.pay;

import rx.Observable;
import sf.hotel.com.data.entity.netresult.pay.PayResult;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/19.
 */
public interface PayEntity {
    Observable<PayResult> callPay(String point);
}
