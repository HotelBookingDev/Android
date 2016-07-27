package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/18.
 */
public class MinPriceBean implements Parcelable{

    @SerializedName("default_s_price")
    private int default_front_price;
    @SerializedName("default_s_point")
    private int default_point;


    @SerializedName("default_d_point")
    private int default_d_point;

    @SerializedName("default_d_price")
    private int default_d_price;



    public MinPriceBean() {
    }

    protected MinPriceBean(Parcel in) {
        default_front_price = in.readInt();
        default_point = in.readInt();
        default_d_point = in.readInt();
        default_d_price = in.readInt();
    }

    public static final Creator<MinPriceBean> CREATOR = new Creator<MinPriceBean>() {
        @Override
        public MinPriceBean createFromParcel(Parcel in) {
            return new MinPriceBean(in);
        }

        @Override
        public MinPriceBean[] newArray(int size) {
            return new MinPriceBean[size];
        }
    };

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

    public int getDefault_d_price() {
        return default_d_price;
    }

    public void setDefault_d_price(int default_d_price) {
        this.default_d_price = default_d_price;
    }

    public int getDefault_d_point() {
        return default_d_point;
    }

    public void setDefault_d_point(int default_d_point) {
        this.default_d_point = default_d_point;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(default_front_price);
        dest.writeInt(default_point);
        dest.writeInt(default_d_point);
        dest.writeInt(default_d_price);
    }
}
