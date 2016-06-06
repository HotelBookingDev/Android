package sf.hotel.com.data.entity;

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
    private String fullname;
    @DatabaseField(columnName = "phone")
    private long phone;
    @DatabaseField(columnName = "sex")
    private int sex;

    public long getUserId() {
        return userId;
    }

    public String getFullname() {
        return fullname;
    }

    public long getPhone() {
        return phone;
    }

    public int getSex() {
        return sex;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.getUserId() + "\n");
        stringBuilder.append("fullname=" + this.getFullname() + "\n");
        stringBuilder.append("sex=" + this.getSex() + "\n");
        stringBuilder.append("phone=" + this.getPhone() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}
