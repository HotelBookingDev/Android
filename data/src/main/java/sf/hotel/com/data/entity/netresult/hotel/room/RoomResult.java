package sf.hotel.com.data.entity.netresult.hotel.room;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/21.
 */
public class RoomResult implements Parcelable{

    @SerializedName("hotel")
    private HotelBean hotelBean;

    protected RoomResult(Parcel in) {
        hotelBean = in.readParcelable(HotelBean.class.getClassLoader());
    }

    public static final Creator<RoomResult> CREATOR = new Creator<RoomResult>() {
        @Override
        public RoomResult createFromParcel(Parcel in) {
            return new RoomResult(in);
        }

        @Override
        public RoomResult[] newArray(int size) {
            return new RoomResult[size];
        }
    };

    public HotelBean getHotelBean() {
        return hotelBean;
    }

    public void setHotelBean(HotelBean hotelBean) {
        this.hotelBean = hotelBean;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(hotelBean, flags);
    }
}
