package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.room.RoomBean;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/18.
 */
public class Hotel1Bean implements Parcelable{
    /**
     * city : 1234
     * hotel_imgs : []
     * introduce : 一家酒店
     * contact_phone : 15726814500
     * name : 无敌酒店
     * min_price : {"default_front_price":130,"default_point":30}
     * active : true
     * id : 2
     * address : address
     */

    private int city;
    private String introduce;
    private String contact_phone;
    private String name;
    /**
     * default_front_price : 130
     * default_point : 30
     */

    private MinPriceBean min_price;
    private boolean active;
    private int id;
    private String address;

    @SerializedName("hotel_imgs")
    private List<String> hotel_imgs;

    @SerializedName("types")
    private List<RoomBean> roomBeanList;

    public Hotel1Bean() {
    }

    protected Hotel1Bean(Parcel in) {
        city = in.readInt();
        introduce = in.readString();
        contact_phone = in.readString();
        name = in.readString();
        min_price = in.readParcelable(MinPriceBean.class.getClassLoader());
        active = in.readByte() != 0;
        id = in.readInt();
        address = in.readString();
        hotel_imgs = in.createStringArrayList();
        roomBeanList = in.createTypedArrayList(RoomBean.CREATOR);
    }

    public static final Creator<Hotel1Bean> CREATOR = new Creator<Hotel1Bean>() {
        @Override
        public Hotel1Bean createFromParcel(Parcel in) {
            return new Hotel1Bean(in);
        }

        @Override
        public Hotel1Bean[] newArray(int size) {
            return new Hotel1Bean[size];
        }
    };

    public List<RoomBean> getRoomBeanList() {
        return roomBeanList;
    }

    public void setRoomBeanList(List<RoomBean> roomBeanList) {
        this.roomBeanList = roomBeanList;
    }




    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MinPriceBean getMin_price() {
        return min_price;
    }

    public void setMin_price(MinPriceBean min_price) {
        this.min_price = min_price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getHotel_imgs() {
        return hotel_imgs;
    }

    public void setHotel_imgs(List<String> hotel_imgs) {
        this.hotel_imgs = hotel_imgs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(city);
        dest.writeString(introduce);
        dest.writeString(contact_phone);
        dest.writeString(name);
        dest.writeParcelable(min_price, flags);
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeInt(id);
        dest.writeString(address);
        dest.writeStringList(hotel_imgs);
        dest.writeTypedList(roomBeanList);
    }
}
