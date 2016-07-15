package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public class HousePackagesBean implements Parcelable{

    /**
     * need_point : 11
     * modified_on : 2016-07-14T17:19:37.237178
     * created_on : 2016-07-14T14:12:20.432010
     * type : 1
     * checked : true
     * deleted : false
     * states : [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
     * id : d985604c-8dde-4b9f-8e59-49cf032a7e60
     * owner : 1
     * breakfast : 1
     * detail : ff
     * front_price : 340
     */

    private int need_point;
    private String modified_on;
    private String created_on;
    private int type;
    private boolean checked;
    private boolean deleted;
    private String id;
    private int owner;
    private int breakfast;
    private String detail;
    private int front_price;
    private List<Integer> states;

    protected HousePackagesBean(Parcel in) {
        need_point = in.readInt();
        modified_on = in.readString();
        created_on = in.readString();
        type = in.readInt();
        checked = in.readByte() != 0;
        deleted = in.readByte() != 0;
        id = in.readString();
        owner = in.readInt();
        breakfast = in.readInt();
        detail = in.readString();
        front_price = in.readInt();
    }

    public static final Creator<HousePackagesBean> CREATOR = new Creator<HousePackagesBean>() {
        @Override
        public HousePackagesBean createFromParcel(Parcel in) {
            return new HousePackagesBean(in);
        }

        @Override
        public HousePackagesBean[] newArray(int size) {
            return new HousePackagesBean[size];
        }
    };

    public int getNeed_point() {
        return need_point;
    }

    public void setNeed_point(int need_point) {
        this.need_point = need_point;
    }

    public String getModified_on() {
        return modified_on;
    }

    public void setModified_on(String modified_on) {
        this.modified_on = modified_on;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getFront_price() {
        return front_price;
    }

    public void setFront_price(int front_price) {
        this.front_price = front_price;
    }

    public List<Integer> getStates() {
        return states;
    }

    public void setStates(List<Integer> states) {
        this.states = states;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(need_point);
        dest.writeString(modified_on);
        dest.writeString(created_on);
        dest.writeInt(type);
        dest.writeByte((byte) (checked ? 1 : 0));
        dest.writeByte((byte) (deleted ? 1 : 0));
        dest.writeString(id);
        dest.writeInt(owner);
        dest.writeInt(breakfast);
        dest.writeString(detail);
        dest.writeInt(front_price);
    }
}