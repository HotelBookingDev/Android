package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public class HousePackagesBean implements Parcelable{
    private int front_price;
    private int need_point;
    private String detail;
    private int id;

    public HousePackagesBean() {
    }

    protected HousePackagesBean(Parcel in) {
        front_price = in.readInt();
        need_point = in.readInt();
        detail = in.readString();
        id = in.readInt();
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

    public int getFront_price() {
        return front_price;
    }

    public void setFront_price(int front_price) {
        this.front_price = front_price;
    }

    public int getNeed_point() {
        return need_point;
    }

    public void setNeed_point(int need_point) {
        this.need_point = need_point;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(front_price);
        dest.writeInt(need_point);
        dest.writeString(detail);
        dest.writeInt(id);
    }
}