package sf.hotel.com.data.entity.netresult;

import com.google.gson.annotations.SerializedName;

import sf.hotel.com.data.entity.UserEntity;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public class LoginResult {
    @SerializedName("UserEntity")
    public UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    @Override

    public String toString() {
        return "LoginResult{" +
                "userEntity=" + userEntity +
                '}';
    }
}
