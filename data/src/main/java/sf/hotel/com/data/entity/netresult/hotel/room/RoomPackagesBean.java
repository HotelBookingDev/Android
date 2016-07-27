package sf.hotel.com.data.entity.netresult.hotel.room;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/20.
 */
public class RoomPackagesBean implements Parcelable{


    /**
     * breakfast : 1
     * id : 2
     * roomstates : [{"need_point":30,"date":"2016-07-20","front_price":130,"state":1},{"need_point":30,"date":"2016-07-21","front_price":130,"state":1}]
     * created_on : 2016-07-19T23:39:16.521460
     * extra : null
     * default_front_price : 130
     * default_point : 30
     */

    private int breakfast;
    private int id;
    private String created_on;
    private Object extra;



    @SerializedName("default_s_price")
    private int default_front_price;

    @SerializedName("default_s_point")
    private int default_point;

    @SerializedName("default_d_point")
    private int default_d_point;

    @SerializedName("default_d_price")
    private int default_d_price;




    /**
     * need_point : 30
     * date : 2016-07-20
     * front_price : 130
     * state : 1
     */

    @SerializedName("roomstates")
    private List<RoomStatusBean> roomStatus;


    protected RoomPackagesBean(Parcel in) {
        breakfast = in.readInt();
        id = in.readInt();
        created_on = in.readString();
        default_front_price = in.readInt();
        default_point = in.readInt();
        default_d_point = in.readInt();
        default_d_price = in.readInt();
        roomStatus = in.createTypedArrayList(RoomStatusBean.CREATOR);
    }

    public static final Creator<RoomPackagesBean> CREATOR = new Creator<RoomPackagesBean>() {
        @Override
        public RoomPackagesBean createFromParcel(Parcel in) {
            return new RoomPackagesBean(in);
        }

        @Override
        public RoomPackagesBean[] newArray(int size) {
            return new RoomPackagesBean[size];
        }
    };

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public int getDefault_front_price() {
        return default_front_price;
    }

    public void setDefault_front_price(int default_front_price) {
        this.default_front_price = default_front_price;
    }

    public int getDefault_point() {
        return default_point;
    }

    public void setDefault_point(int default_point) {
        this.default_point = default_point;
    }

    public List<RoomStatusBean> getRoomstates() {
        return roomStatus;
    }

    public void setRoomstates(List<RoomStatusBean> roomStatus) {
        this.roomStatus = roomStatus;
    }


    public int getDefault_d_point() {
        return default_d_point;
    }

    public void setDefault_d_point(int default_d_point) {
        this.default_d_point = default_d_point;
    }

    public int getDefault_d_price() {
        return default_d_price;
    }

    public void setDefault_d_price(int default_d_price) {
        this.default_d_price = default_d_price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(breakfast);
        dest.writeInt(id);
        dest.writeString(created_on);
        dest.writeInt(default_front_price);
        dest.writeInt(default_point);
        dest.writeInt(default_d_point);
        dest.writeInt(default_d_price);
        dest.writeTypedList(roomStatus);
    }
}
