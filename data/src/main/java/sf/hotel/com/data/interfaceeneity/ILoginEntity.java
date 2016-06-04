package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import sf.hotel.com.data.eneity.LoginEntity;
import sf.hotel.com.data.eneity.StateEntity;

/**
 * Created by FMT on 2016/6/3:19:29
 * EMAILE 1105896230@qq.com.
 */
public interface ILoginEntity {
    Observable<StateEntity<LoginEntity>> login(String username, String password);
}
