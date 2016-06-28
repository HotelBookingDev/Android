package sf.hotel.com.data.interfaceeneity.person;

import rx.Observable;
import sf.hotel.com.data.entity.netresult.TokenResult;

/**
 * Created by 林其望
 * data：2016/6/22
 * email: 1105896230@qq.com
 */
public interface IUserInfoEntity {
    Observable<TokenResult> getToken();
}
