package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import sf.hotel.com.data.entity.netresult.TokenResult;

/**
 * Created by 林其望 on 2016/6/22.
 */
public interface IUserInfoEntity {
    Observable<TokenResult> getToken();

}
