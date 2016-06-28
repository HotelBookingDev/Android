package sf.hotel.com.data.entity.netresult;

import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
@DatabaseTable(tableName = "tb_hotelresult")
public class HotelResult implements Serializable{
    /**
     * id : 1
     * hotelLogoImgs : [{"img_url":"http://www.kaiyuanhotels.com/uploads/GW_HOTEL_PIC_A/1603311658416419.jpg","hotel":1},{"img_url":"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCADcAW4DASI","hotel":1},{"img_url":"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E9%85%92%E5%BA%97%E6%88%BF%E9%97%B4%E5%9B%BE%E7%89%87300%2A300&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=2539667609,3228311465&os=3230286096,1343286937&simid=31687","hotel":1},{"img_url":"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E9%85%92%E5%BA%97%E6%88%BF%E9%97%B4%E5%9B%BE%E7%89%87300%2A300&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=1289292725,2049668828&os=2762675889,1093042713&simid=41599","hotel":1},{"img_url":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%85%92%E5%BA%97%E6%88%BF%E9%97%B4%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1598272522,2986981968&os=4025898905,4267432034&simi","hotel":1}]
     * name : 浙商开元名都酒店
     * address : 杭州市余杭区临平迎宾大道535号
     * introduce : 浙商开元名都酒店由浙江余杭高速公路有限责任公司投资，由中国最大的民营高星级连锁酒店集团——开元酒店集团全权委托管理，是集餐饮、客房、休闲及会议为一体的按五星级标准建造的酒店，为余杭区新城现代建筑集群中的标志性景观建筑。 酒店位于临平新城中心地带，地理位置优越，交通便捷，商业配套充足，与沪杭甬高速公路相邻，至萧山国际机场仅35分钟车程，距杭州西湖至美仅30分钟车程，与地铁一号线南苑站仅百米之隔，与杭州高铁站、火车东站及杭州城站无缝链接，真正实现零换乘
     * contact_phone : 0571-88578888
     * city : 1
     */

    private List<HotelsBean> hotels;

    public List<HotelsBean> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelsBean> hotels) {
        this.hotels = hotels;
    }

    public static class HotelsBean implements Serializable{
        private int id;
        private String name;
        private String address;
        private String introduce;
        private String contact_phone;
        private int city;
        /**
         * img_url : http://www.kaiyuanhotels.com/uploads/GW_HOTEL_PIC_A/1603311658416419.jpg
         * hotel : 1
         */

        private List<HotelLogoImgsBean> hotelLogoImgs;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getContact_phone() {
            return contact_phone;
        }

        public void setContact_phone(String contact_phone) {
            this.contact_phone = contact_phone;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public List<HotelLogoImgsBean> getHotelLogoImgs() {
            return hotelLogoImgs;
        }

        public void setHotelLogoImgs(List<HotelLogoImgsBean> hotelLogoImgs) {
            this.hotelLogoImgs = hotelLogoImgs;
        }

        public static class HotelLogoImgsBean implements Serializable{
            private String img_url;
            private int hotel;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public int getHotel() {
                return hotel;
            }

            public void setHotel(int hotel) {
                this.hotel = hotel;
            }
        }
    }
}
