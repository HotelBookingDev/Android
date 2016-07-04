package sf.hotel.com.data.entity.netresult;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.data.entity.netresult.hotel.HousePackagesBean;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
@DatabaseTable(tableName = "tb_hotelresult")
public class HotelResult implements Parcelable {

    /**
     * id : 1
     * house_imgs : []
     * hotel_houses : [{"id":1,"house_imgs":[],"housePackages":[{"need_point":20,"front_price":350,"package_state":"1","room_avaliable":0,"detail":"细节"}],"name":"商务大床"}]
     * name : 酒店名
     * address : 地址
     * introduce : 介绍
     * contact_phone : 123456
     * city : 1
     */

    private List<HotelsBean> hotels;

    protected HotelResult(Parcel in) {
        hotels = in.createTypedArrayList(HotelsBean.CREATOR);
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

    public List<HotelsBean> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelsBean> hotels) {
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
