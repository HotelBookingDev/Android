package sf.hotel.com.data.interfaceeneity;

import sf.hotel.com.data.eneity.LoginEntity;
import sf.hotel.com.data.eneity.StateEntity;
import sf.hotel.com.data.utils.CheckUtils;

/**
 * Created by FMT on 2016/6/3:19:44
 * EMAILE 1105896230@qq.com.
 */
public class LoginEntityImp implements ILoginEntity {
    @Override
    public rx.Observable<StateEntity<LoginEntity>> login(String username, String password) {

        return rx.Observable.just(init(username, password)).filter(loginEntityStateEntity -> {
            boolean b = CheckUtils.checkPhoneNumber(
                    loginEntityStateEntity.getData().getUsername()) &&
                    !CheckUtils.isTextViewEmpty(loginEntityStateEntity.getData().getPassword());
            if (!b) {
                loginEntityStateEntity.setCode(-1);
            }
            return b;
        }).doOnNext(loginEntityStateEntity -> {
            //new work
        });
    }

    private StateEntity<LoginEntity> init(String username, String password) {
        LoginEntity loginEntity = new LoginEntity(username, password);
        return new StateEntity<>(loginEntity);
    }
}
