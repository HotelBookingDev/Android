package sf.hotel.com.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ProvincesResult implements Parcelable {

    /**
     * id : 1
     * citys : [{"id":1,"code":1001,"name":"宁波","name_py":"ningbo","logo":"http://img4.imgtn.bdimg.com/it/u=2524053065,1600155239&fm=21&gp=0.jpg"},{"id":2,"code":1002,"name":"杭州","name_py":"hangzhou","logo":"http://img4.imgtn.bdimg.com/it/u=2524053065,1600155239&fm=21&gp=0.jpg"}]
     * name : 浙江
     * name_py : ZheJiang
     */

    private List<ProvincesBean> provinces;

    protected ProvincesResult(Parcel in) {
        provinces = in.createTypedArrayList(ProvincesBean.CREATOR);
    }

    public static final Creator<ProvincesResult> CREATOR = new Creator<ProvincesResult>() {
        @Override
        public ProvincesResult createFromParcel(Parcel in) {
            return new ProvincesResult(in);
        }

        @Override
        public ProvincesResult[] newArray(int size) {
            return new ProvincesResult[size];
        }
    };

    public List<ProvincesBean> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvincesBean> provinces) {
        this.provinces = provinces;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(provinces);
    }
}
