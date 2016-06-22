package sf.hotel.com.data.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by FMT on 2016/6/3:15:55
 * EMAILE 1105896230@qq.com.
 */
@DatabaseTable(tableName = "tb_user")
public class UserEntity {

    @DatabaseField(generatedId = true)
    private long userId;

    @DatabaseField(columnName = "name")
    @SerializedName("name")
    private String fullname;

    @SerializedName("phone_number")
    @DatabaseField(columnName = "phoneNumber")
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

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", fullname='" + fullname + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", sex=" + sex +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
