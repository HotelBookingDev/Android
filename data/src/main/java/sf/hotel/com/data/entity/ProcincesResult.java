package sf.hotel.com.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ProcincesResult  implements Serializable{
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

        private List<CitysBean> citys;

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

        public List<CitysBean> getCitys() {
            return citys;
        }

        public void setCitys(List<CitysBean> citys) {
            this.citys = citys;
        }

        public static class CitysBean implements Serializable{
            private int id;
            private String name;
            private String name_py;
            private String logo;

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
