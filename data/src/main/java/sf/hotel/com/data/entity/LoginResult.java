package sf.hotel.com.data.entity;

import com.google.gson.annotations.SerializedName;

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
