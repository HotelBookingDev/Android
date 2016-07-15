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

    /**
     * id : 2
     * house_imgs : []
     * name : 神马房型
     * enabled : true
     * housePackages : [{"need_point":11,"modified_on":"2016-07-14T17:19:37.237178","created_on":"2016-07-14T14:12:20.432010","type":1,"checked":true,"deleted":false,"states":[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],"id":"d985604c-8dde-4b9f-8e59-49cf032a7e60","owner":1,"breakfast":1,"detail":"ff","front_price":340},{"need_point":12,"modified_on":"2016-07-14T16:20:42.412610","created_on":"2016-07-14T16:20:42.412108","type":1,"checked":false,"deleted":false,"states":[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],"id":"b658b88c-8c03-4736-b954-e5fe517ebf44","owner":2,"breakfast":1,"detail":"","front_price":300}]
     * hotel : 2
     * checked : true
     */

    private int id;
    private String name;
    private boolean enabled;
    private int hotel;
    private boolean checked;
    private List<String> house_imgs;
    /**
     * need_point : 11
     * modified_on : 2016-07-14T17:19:37.237178
     * created_on : 2016-07-14T14:12:20.432010
     * type : 1
     * checked : true
     * deleted : false
     * states : [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
     * id : d985604c-8dde-4b9f-8e59-49cf032a7e60
     * owner : 1
     * breakfast : 1
     * detail : ff
     * front_price : 340
     */

    private List<HousePackagesBean> housePackages;

    protected HotelHousesBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        enabled = in.readByte() != 0;
        hotel = in.readInt();
        checked = in.readByte() != 0;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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
        dest.writeByte((byte) (enabled ? 1 : 0));
        dest.writeInt(hotel);
        dest.writeByte((byte) (checked ? 1 : 0));
        dest.writeStringList(house_imgs);
        dest.writeTypedList(housePackages);
    }
}
