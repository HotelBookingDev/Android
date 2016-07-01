package sf.hotel.com.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ProvincesResult implements Serializable {

    /**
     * id : 1
     * citys : [{"id":1,"code":1001,"name":"宁波","name_py":"ningbo","logo":"http://img4.imgtn.bdimg.com/it/u=2524053065,1600155239&fm=21&gp=0.jpg"},{"id":2,"code":1002,"name":"杭州","name_py":"hangzhou","logo":"http://img4.imgtn.bdimg.com/it/u=2524053065,1600155239&fm=21&gp=0.jpg"}]
     * name : 浙江
     * name_py : ZheJiang
     */

    private List<ProvincesBean> provinces;

    public List<ProvincesBean> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvincesBean> provinces) {
        this.provinces = provinces;
    }

    public static class ProvincesBean implements Serializable {
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

        public static class CityBean implements Serializable {
            private int id;
            private int code;
            private String name;
            private String name_py;
            private String logo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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
        }
    }
}
