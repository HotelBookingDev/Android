package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/28.
 */
public class Images implements Parcelable {

    private String img;

    protected Images(Parcel in) {
        img = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(img);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Images> CREATOR = new Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel in) {
            return new Images(in);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
