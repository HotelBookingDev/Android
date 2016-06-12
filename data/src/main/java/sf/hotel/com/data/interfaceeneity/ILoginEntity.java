package sf.hotel.com.data.interfaceeneity;

import rx.Observable;
import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.entity.UserEntity;

/**
 * Created by FMT on 2016/6/3:19:29
 * EMAILE 1105896230@qq.com.
 */
public interface ILoginEntity {
    Observable<UserEntity>login(String username, String password);
}
