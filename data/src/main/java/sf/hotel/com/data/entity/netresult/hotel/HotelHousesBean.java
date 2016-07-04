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
    private String name;
    private List<String> house_imgs;
    /**
     * need_point : 20
     * front_price : 350
     * package_state : 1
     * room_avaliable : 0
     * detail : 细节
     */

    private List<HousePackagesBean> housePackages;

    protected HotelHousesBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        house_imgs = in.createStringArrayList();
        housePackages = in.createTypedArrayList(HousePackagesBean.CREATOR);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeStringList(house_imgs);
        dest.writeTypedList(housePackages);
    }
}
