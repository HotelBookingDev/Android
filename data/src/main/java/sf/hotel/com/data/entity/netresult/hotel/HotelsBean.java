package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public class HotelsBean implements Parcelable{
    private int id;
    private String name;
    private String address;
    private String introduce;
    private String contact_phone;
    private int city;
    private List<String> house_imgs;
    /**
     * id : 1
     * house_imgs : []
     * housePackages : [{"need_point":20,"front_price":350,"package_state":"1","room_avaliable":0,"detail":"细节"}]
     * name : 商务大床
     */

    private List<HotelHousesBean> hotel_houses;

    protected HotelsBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        introduce = in.readString();
        contact_phone = in.readString();
        city = in.readInt();
        house_imgs = in.createStringArrayList();
    }

    public static final Creator<HotelsBean> CREATOR = new Creator<HotelsBean>() {
        @Override
        public HotelsBean createFromParcel(Parcel in) {
            return new HotelsBean(in);
        }

        @Override
        public HotelsBean[] newArray(int size) {
            return new HotelsBean[size];
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public List<String> getHouse_imgs() {
        return house_imgs;
    }

    public void setHouse_imgs(List<String> house_imgs) {
        this.house_imgs = house_imgs;
    }

    public List<HotelHousesBean> getHotel_houses() {
        return hotel_houses;
    }

    public void setHotel_houses(List<HotelHousesBean> hotel_houses) {
        this.hotel_houses = hotel_houses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(introduce);
        dest.writeString(contact_phone);
        dest.writeInt(city);
        dest.writeStringList(house_imgs);
    }
}