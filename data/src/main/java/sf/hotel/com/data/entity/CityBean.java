package sf.hotel.com.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import sf.hotel.com.data.utils.pinyin.PinyinUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public class CityBean implements Parcelable, Comparable{
    private int code;
    private String name = "杭州";
    private String name_py;
    private String logo;

    public CityBean() {}

    protected CityBean(Parcel in) {
        code = in.readInt();
        name = in.readString();
        name_py = in.readString();
        logo = in.readString();
    }

    public static final Creator<CityBean> CREATOR = new Creator<CityBean>() {
        @Override
        public CityBean createFromParcel(Parcel in) {
            return new CityBean(in);
        }

        @Override
        public CityBean[] newArray(int size) {
            return new CityBean[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(name);
        dest.writeString(name_py);
        dest.writeString(logo);
    }


    @Override
    public int compareTo(Object another) {
        if (another instanceof CityBean){
            CityBean anoth = (CityBean) another;
            String anPy = PinyinUtils.getPingYin(anoth.getName());
            String currPy = PinyinUtils.getPingYin(name);
            return currPy.compareTo(anPy);
        }else {
            throw new IllegalArgumentException();
        }
    }
}