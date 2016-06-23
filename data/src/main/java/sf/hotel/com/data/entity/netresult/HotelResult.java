package sf.hotel.com.data.entity.netresult;

import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class HotelResult {

    /**
     * id : 1
     * hotelLogoImgs : [{"img_url":"http://www.kaiyuanhotels.com/uploads/GW_HOTEL_PIC_A/1603311658416419.jpg","hotel":1}]
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

    public static class HotelsBean {
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

        public static class HotelLogoImgsBean {
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
