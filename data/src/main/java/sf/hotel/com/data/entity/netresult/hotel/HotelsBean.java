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
    private String address;
    private String name;
    private int city;
    private String contact_phone;
    private String introduce;
    private int id;
    private List<String> hotel_imgs;
    /**
     * id : 1
     */

    private List<HotelHousesBean> hotel_houses;


    public HotelsBean(){}

    protected HotelsBean(Parcel in) {
        address = in.readString();
        name = in.readString();
        city = in.readInt();
        contact_phone = in.readString();
        introduce = in.readString();
        id = in.readInt();
        hotel_houses = in.createTypedArrayList(HotelHousesBean.CREATOR);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getHotel_imgs() {
        return hotel_imgs;
    }

    public void setHotel_imgs(List<String> hotel_imgs) {
        this.hotel_imgs = hotel_imgs;
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
        dest.writeString(address);
        dest.writeString(name);
        dest.writeInt(city);
        dest.writeString(contact_phone);
        dest.writeString(introduce);
        dest.writeInt(id);
        dest.writeTypedList(hotel_houses);
    }
}