package sf.hotel.com.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public class ProvincesBean implements Parcelable {
    private int id;
    private String name;
    private String name_py;
    /**
     * id : 1
     * code : 1001
     * name : 宁波
     * name_py : ningbo
     * logo : http://img4.imgtn.bdimg.com/it/u=2524053065,1600155239&fm=21&gp=0.jpg
     */

    private List<CityBean> citys;

    protected ProvincesBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        name_py = in.readString();
        citys = in.createTypedArrayList(CityBean.CREATOR);
    }

    public static final Creator<ProvincesBean> CREATOR = new Creator<ProvincesBean>() {
        @Override
        public ProvincesBean createFromParcel(Parcel in) {
            return new ProvincesBean(in);
        }

        @Override
        public ProvincesBean[] newArray(int size) {
            return new ProvincesBean[size];
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

    public String getName_py() {
        return name_py;
    }

    public void setName_py(String name_py) {
        this.name_py = name_py;
    }

    public List<CityBean> getCitys() {
        return citys;
    }

    public void setCitys(List<CityBean> citys) {
        this.citys = citys;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(name_py);
        dest.writeTypedList(citys);
    }
}
