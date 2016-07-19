package sf.hotel.com.data.interfaceeneity.pay;

import rx.Observable;
import sf.hotel.com.data.entity.netresult.pay.PayResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/19.
 */
public class IPayEntityImp implements PayEntity {

    public Observable<PayResult> callPay(String point){
        return ApiWrapper.getInstance().callPay(point);
    }
}
