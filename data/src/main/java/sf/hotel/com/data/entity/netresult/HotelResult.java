package sf.hotel.com.data.entity.netresult;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.Hotel1Bean;
import sf.hotel.com.data.entity.netresult.hotel.HotelHousesBean;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
@DatabaseTable(tableName = "tb_hotelresult")
public class HotelResult implements Parcelable {

    /**
     * address : 地址
     * name : 酒店名
     * city : 1
     * hotel_imgs : []
     * contact_phone : 123456
     * introduce : 介绍
     * id : 1
     * hotel_houses : [{"id":1}]
     */

    private List<Hotel1Bean> hotels;

    protected HotelResult(Parcel in) {
        hotels = in.createTypedArrayList(Hotel1Bean.CREATOR);
    }

    public static final Creator<HotelResult> CREATOR = new Creator<HotelResult>() {
        @Override
        public HotelResult createFromParcel(Parcel in) {
            return new HotelResult(in);
        }

        @Override
        public HotelResult[] newArray(int size) {
            return new HotelResult[size];
        }
    };


    public List<Hotel1Bean> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel1Bean> hotels) {
        this.hotels = hotels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(hotels);
    }
}
