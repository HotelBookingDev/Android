package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/18.
 */
public class RoomBean implements Parcelable{

    /**
     * name : 商务爆炸大床
     * id : 2
     */

    private String name;
    private int id;

    public RoomBean() {
    }

    protected RoomBean(Parcel in) {
        name = in.readString();
        id = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }
}
