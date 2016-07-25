package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/25.
 */

public class HotelImgsBean implements Parcelable {
    private String img;

    protected HotelImgsBean(Parcel in) {
        img = in.readString();
    }

    public static final Creator<HotelImgsBean> CREATOR = new Creator<HotelImgsBean>() {
        @Override
        public HotelImgsBean createFromParcel(Parcel in) {
            return new HotelImgsBean(in);
        }

        @Override
        public HotelImgsBean[] newArray(int size) {
            return new HotelImgsBean[size];
        }
    };

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(img);
    }
}