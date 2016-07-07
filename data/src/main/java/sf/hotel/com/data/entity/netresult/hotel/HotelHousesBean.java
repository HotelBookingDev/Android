package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public  class HotelHousesBean implements Parcelable{
    private int id;

    public HotelHousesBean() {
    }

    protected HotelHousesBean(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<HotelHousesBean> CREATOR = new Creator<HotelHousesBean>() {
        @Override
        public HotelHousesBean createFromParcel(Parcel in) {
            return new HotelHousesBean(in);
        }

        @Override
        public HotelHousesBean[] newArray(int size) {
            return new HotelHousesBean[size];
        }
    };

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
        dest.writeInt(id);
    }
}
