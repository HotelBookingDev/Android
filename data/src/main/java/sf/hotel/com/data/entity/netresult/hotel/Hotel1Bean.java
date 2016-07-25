package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/18.
 */
public class Hotel1Bean implements Parcelable{

    /**
     * contact_phone : 15726814500
     * modified : 2016-07-22T12:05:31.358613
     * address : address
     * name : 无敌酒店
     * cover_img : http://qiniu.agesd.com/1
     * hotel_imgs : [{"img":"http://qiniu.agesd.com/hotel1.png"}]
     * min_price : {"default_front_price":1,"default_point":1}
     * id : 1
     * Smoking : false
     * active : true
     * introduce : 一家酒店
     */

    private String contact_phone;
    private String modified;
    private String address;
    private String name;
    private String cover_img;
    /**
     * default_front_price : 1
     * default_point : 1
     */

    private MinPriceBean min_price;
    private int id;
    private boolean Smoking;
    private boolean active;
    private String introduce;
    /**
     * img : http://qiniu.agesd.com/hotel1.png
     */

    private List<HotelImgsBean> hotel_imgs;


    public Hotel1Bean() {
    }

    protected Hotel1Bean(Parcel in) {
        contact_phone = in.readString();
        modified = in.readString();
        address = in.readString();
        name = in.readString();
        cover_img = in.readString();
        min_price = in.readParcelable(MinPriceBean.class.getClassLoader());
        id = in.readInt();
        Smoking = in.readByte() != 0;
        active = in.readByte() != 0;
        introduce = in.readString();
        hotel_imgs = in.createTypedArrayList(HotelImgsBean.CREATOR);
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

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

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

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public MinPriceBean getMin_price() {
        return min_price;
    }

    public void setMin_price(MinPriceBean min_price) {
        this.min_price = min_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSmoking() {
        return Smoking;
    }

    public void setSmoking(boolean Smoking) {
        this.Smoking = Smoking;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<HotelImgsBean> getHotel_imgs() {
        return hotel_imgs;
    }

    public void setHotel_imgs(List<HotelImgsBean> hotel_imgs) {
        this.hotel_imgs = hotel_imgs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contact_phone);
        dest.writeString(modified);
        dest.writeString(address);
        dest.writeString(name);
        dest.writeString(cover_img);
        dest.writeParcelable(min_price, flags);
        dest.writeInt(id);
        dest.writeByte((byte) (Smoking ? 1 : 0));
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeString(introduce);
        dest.writeTypedList(hotel_imgs);
    }
}
