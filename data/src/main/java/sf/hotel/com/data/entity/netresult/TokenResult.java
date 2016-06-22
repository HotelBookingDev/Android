package sf.hotel.com.data.entity.netresult;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 林其望 on 2016/6/22.
 */
public class TokenResult {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @SerializedName("token")
    private String token;
}
