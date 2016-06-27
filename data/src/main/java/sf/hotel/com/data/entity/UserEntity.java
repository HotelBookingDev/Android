package sf.hotel.com.data.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FMT on 2016/6/3:15:55
 * email 1105896230@qq.com.
 */
@DatabaseTable(tableName = "tb_user")
public class UserEntity {

    @DatabaseField(generatedId = true, columnName = "id")
    @SerializedName("id")
    private long userId;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @DatabaseField(columnName = "name")
    @SerializedName("name")
    private String fullname;

    @SerializedName("phone_number")
    @DatabaseField(columnName = "phone_number")
    private long phoneNumber;

    @DatabaseField(columnName = "sex")
    private int sex;

    @SerializedName("create_at")
    private String createTime;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @SerializedName("avatar")
    private String avatar;

    public long getUserId() {
        return userId;
    }

    public String getFullname() {
        return fullname;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public int getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", phoneNumber=" + phoneNumber +
                ", sex=" + sex +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
