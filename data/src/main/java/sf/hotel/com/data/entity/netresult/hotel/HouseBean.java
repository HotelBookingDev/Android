package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public class HouseBean implements Parcelable{
    private String name;
    private int hotel;
    private int id;
    private List<String> house_imgs;
    /**
     * front_price : 350
     * need_point : 20
     * detail : 细节
     * id : 1
     */

    private List<HousePackagesBean> housePackages;

    public HouseBean() {
    }

    protected HouseBean(Parcel in) {
        name = in.readString();
        hotel = in.readInt();
        id = in.readInt();
    }

    public static final Creator<HouseBean> CREATOR = new Creator<HouseBean>() {
        @Override
        public HouseBean createFromParcel(Parcel in) {
            return new HouseBean(in);
        }

        @Override
        public HouseBean[] newArray(int size) {
            return new HouseBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getHouse_imgs() {
        return house_imgs;
    }

    public void setHouse_imgs(List<String> house_imgs) {
        this.house_imgs = house_imgs;
    }

    public List<HousePackagesBean> getHousePackages() {
        return housePackages;
    }

    public void setHousePackages(List<HousePackagesBean> housePackages) {
        this.housePackages = housePackages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(hotel);
        dest.writeInt(id);
    }

}
