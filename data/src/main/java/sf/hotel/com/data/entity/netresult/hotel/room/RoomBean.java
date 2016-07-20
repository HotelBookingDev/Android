package sf.hotel.com.data.entity.netresult.hotel.room;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/18.
 */
public class RoomBean implements Parcelable{

    /**
     * name : 商务爆炸大床
     * id : 1
     * hotel : 1
     * checked : true
     * roomPackages : [{"breakfast":1,"id":2,"roomstates":[{"need_point":30,"date":"2016-07-20","front_price":130,"state":1},{"need_point":30,"date":"2016-07-21","front_price":130,"state":1}],"created_on":"2016-07-19T23:39:16.521460","extra":null,"default_front_price":130,"default_point":30},{"breakfast":1,"id":3,"roomstates":[{"need_point":30,"date":"2016-07-20","front_price":130,"state":1},{"need_point":30,"date":"2016-07-21","front_price":130,"state":1}],"created_on":"2016-07-19T23:40:54.653244","extra":null,"default_front_price":130,"default_point":30},{"breakfast":1,"id":4,"roomstates":[{"need_point":30,"date":"2016-07-20","front_price":130,"state":1},{"need_point":30,"date":"2016-07-21","front_price":130,"state":1}],"created_on":"2016-07-19T23:41:03.149952","extra":null,"default_front_price":130,"default_point":30},{"breakfast":1,"id":5,"roomstates":[{"need_point":30,"date":"2016-07-20","front_price":130,"state":1},{"need_point":30,"date":"2016-07-21","front_price":130,"state":1}],"created_on":"2016-07-19T23:41:04.347405","extra":null,"default_front_price":130,"default_point":30}]
     * room_imgs : []
     * active : true
     */

    private String name;
    private int id;
    private int hotel;
    private boolean checked;
    private boolean active;
    /**
     * breakfast : 1
     * id : 2
     * roomstates : [{"need_point":30,"date":"2016-07-20","front_price":130,"state":1},{"need_point":30,"date":"2016-07-21","front_price":130,"state":1}]
     * created_on : 2016-07-19T23:39:16.521460
     * extra : null
     * default_front_price : 130
     * default_point : 30
     */

    private List<RoomPackagesBean> roomPackages;
    private List<String> room_imgs;

    protected RoomBean(Parcel in) {
        name = in.readString();
        id = in.readInt();
        hotel = in.readInt();
        checked = in.readByte() != 0;
        active = in.readByte() != 0;
        roomPackages = in.createTypedArrayList(RoomPackagesBean.CREATOR);
        room_imgs = in.createStringArrayList();
    }

    public static final Creator<RoomBean> CREATOR = new Creator<RoomBean>() {
        @Override
        public RoomBean createFromParcel(Parcel in) {
            return new RoomBean(in);
        }

        @Override
        public RoomBean[] newArray(int size) {
            return new RoomBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RoomPackagesBean> getRoomPackages() {
        return roomPackages;
    }

    public void setRoomPackages(List<RoomPackagesBean> roomPackages) {
        this.roomPackages = roomPackages;
    }

    public List<String> getRoom_imgs() {
        return room_imgs;
    }

    public void setRoom_imgs(List<String> room_imgs) {
        this.room_imgs = room_imgs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeInt(hotel);
        dest.writeByte((byte) (checked ? 1 : 0));
        dest.writeByte((byte) (active ? 1 : 0));
        dest.writeTypedList(roomPackages);
        dest.writeStringList(room_imgs);
    }
}
