package sf.hotel.com.data.interfaceeneity;

/**
 * Created by FMT on 2016/6/3:19:44
 * EMAILE 1105896230@qq.com.
 */
public class LoginEntityImp implements ILoginEntity {
    @Override
    public void login(String username, String password, onLoginLinstener monLoginLinstenr) {
        monLoginLinstenr.success();
    }
}
