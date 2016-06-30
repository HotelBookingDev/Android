package sf.hotel.com.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ProcincesResult implements Serializable {
    /**
     * id : 1
     * citys : [{"id":1,"name":"HangZhou","name_py":"杭州","logo":"http://img4.imgtn.bdimg.com/it/u=2524053065,1600155239&fm=21&gp=0.jpg"},{"id":2,"name":"NingBo","name_py":"宁波","logo":"http://img4.imgtn.bdimg.com/it/u=2524053065,1600155239&fm=21&gp=0.jpg"}]
     * name : ZheJiang
     * name_py : 浙江
     */

    private List<ProcincesBean> procinces;

    public List<ProcincesBean> getProcinces() {
        return procinces;
    }

    public void setProcinces(List<ProcincesBean> procinces) {
        this.procinces = procinces;
    }

    public static class ProcincesBean {
        private int id;
        private String name;
        private String name_py;
        /**
         * id : 1
         * name : HangZhou
         * name_py : 杭州
         * logo : http://img4.imgtn.bdimg.com/it/u=2524053065,1600155239&fm=21&gp=0.jpg
         */

        private List<CityBean> citys;

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

        public static class CityBean implements Parcelable {
            private int id;
            private String name;
            private String name_py;
            private String logo;

            public CityBean() {
            }

            protected CityBean(Parcel in) {
                id = in.readInt();
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
                dest.writeInt(id);
                dest.writeString(name);
                dest.writeString(name_py);
                dest.writeString(logo);
            }
        }

        @Override
        public String toString() {
            return "ProcincesBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", name_py='" + name_py + '\'' +
                    ", citys=" + citys +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProcincesResult{" +
                "procinces=" + procinces +
                '}';
    }
}
