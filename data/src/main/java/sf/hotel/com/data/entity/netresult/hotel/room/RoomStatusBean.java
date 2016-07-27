package sf.hotel.com.data.entity.netresult.hotel.room;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/20.
 */
public class RoomStatusBean  implements Parcelable{
    /**
     * need_point : 30
     * date : 2016-07-20
     * front_price : 130
     * state : 1
     */

    @SerializedName("s_point")
    private int need_point;

    @SerializedName("s_price")
    private int front_price;


    @SerializedName("d_point")
    private int d_point;

    @SerializedName("d_price")
    private int d_price;

    private String date;

    private int state;

    public RoomStatusBean() {
    }

    protected RoomStatusBean(Parcel in) {
        need_point = in.readInt();
        front_price = in.readInt();
        d_point = in.readInt();
        d_price = in.readInt();
        date = in.readString();
        state = in.readInt();
    }

    public static final Creator<RoomStatusBean> CREATOR = new Creator<RoomStatusBean>() {
        @Override
        public RoomStatusBean createFromParcel(Parcel in) {
            return new RoomStatusBean(in);
        }

        @Override
        public RoomStatusBean[] newArray(int size) {
            return new RoomStatusBean[size];
        }
    };

    public int getNeed_point() {
        return need_point;
    }

    public void setNeed_point(int need_point) {
        this.need_point = need_point;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFront_price() {
        return front_price;
    }

    public void setFront_price(int front_price) {
        this.front_price = front_price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public int getD_point() {
        return d_point;
    }

    public void setD_point(int d_point) {
        this.d_point = d_point;
    }

    public int getD_price() {
        return d_price;
    }

    public void setD_price(int d_price) {
        this.d_price = d_price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(need_point);
        dest.writeInt(front_price);
        dest.writeInt(d_point);
        dest.writeInt(d_price);
        dest.writeString(date);
        dest.writeInt(state);
    }
}
