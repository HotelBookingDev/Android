package sf.hotel.com.data.entity.netresult.hotel.room;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.Images;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/21.
 */
public class HotelBean implements Parcelable{
    private int id;
    private String address;
    private String name;
    private boolean active;
    private String modified;
    private String contact_phone;
    private String introduce;


    @SerializedName("hotel_imgs")
    private List<Images> imagesList;

    @SerializedName("smoking")
    private boolean smoking;

    protected HotelBean(Parcel in) {
        id = in.readInt();
        address = in.readString();
        name = in.readString();
        active = in.readByte() != 0;
        modified = in.readString();
        contact_phone = in.readString();
        introduce = in.readString();
        imagesList = in.createTypedArrayList(Images.CREATOR);
        smoking = in.readByte() != 0;
        rooms = in.createTypedArrayList(RoomBean.CREATOR);
    }

    public static final Creator<HotelBean> CREATOR = new Creator<HotelBean>() {
        @Override
        public HotelBean createFromParcel(Parcel in) {
            return new HotelBean(in);
        }

        @Override
        public HotelBean[] newArray(int size) {
            return new HotelBean[size];
        }
    };

    public List<Images> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @SerializedName("rooms")
    private List<RoomBean> rooms;

    public HotelBean() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
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

    public List<RoomBean> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomBean> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(address);
        dest.writeString(name);
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeString(modified);
        dest.writeString(contact_phone);
        dest.writeString(introduce);
        dest.writeTypedList(imagesList);
        dest.writeByte((byte) (smoking ? 1 : 0));
        dest.writeTypedList(rooms);
    }
}
