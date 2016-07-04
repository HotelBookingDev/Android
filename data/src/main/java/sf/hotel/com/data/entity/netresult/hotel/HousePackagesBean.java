package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public class HousePackagesBean implements Parcelable{
    private int need_point;
    private int front_price;
    private String package_state;
    private int room_avaliable;
    private String detail;

    protected HousePackagesBean(Parcel in) {
        need_point = in.readInt();
        front_price = in.readInt();
        package_state = in.readString();
        room_avaliable = in.readInt();
        detail = in.readString();
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

    public int getFront_price() {
        return front_price;
    }

    public void setFront_price(int front_price) {
        this.front_price = front_price;
    }

    public String getPackage_state() {
        return package_state;
    }

    public void setPackage_state(String package_state) {
        this.package_state = package_state;
    }

    public int getRoom_avaliable() {
        return room_avaliable;
    }

    public void setRoom_avaliable(int room_avaliable) {
        this.room_avaliable = room_avaliable;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(need_point);
        dest.writeInt(front_price);
        dest.writeString(package_state);
        dest.writeInt(room_avaliable);
        dest.writeString(detail);
    }
}