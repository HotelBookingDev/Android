package sf.hotel.com.data.entity.netresult.hotel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public class TypesBean implements Parcelable{
    private int id;
    private String name;

    public TypesBean() {
    }

    protected TypesBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<TypesBean> CREATOR = new Creator<TypesBean>() {
        @Override
        public TypesBean createFromParcel(Parcel in) {
            return new TypesBean(in);
        }

        @Override
        public TypesBean[] newArray(int size) {
            return new TypesBean[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}