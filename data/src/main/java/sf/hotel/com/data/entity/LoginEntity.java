package sf.hotel.com.data.entity;

/**
 * Created by FMT on 2016/6/3:19:31
 * EMAILE 1105896230@qq.com.
 */
public class LoginEntity {
    private String username;
    private String password;

    public LoginEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
