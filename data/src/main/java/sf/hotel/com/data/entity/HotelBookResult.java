package sf.hotel.com.data.entity;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/12.
 */
public class HotelBookResult {

    /**
     * number : 10021607121522
     * modified_by : null
     * created_on : 2016-07-12T15:50:09.547411
     * comment : null
     * modified_on : 2016-07-12T15:50:09.547445
     * label :
     * checkin_time : 2016-07-14
     * product : 4d587e07-8704-4048-b59f-41067528e2c0
     * seller : 2
     * uuid : b6c34999-ed4f-445e-89e0-2fd0d168928f
     * process_state : 1
     * request_notes : 需要双早
     * id : 29
     * payment_status : 1
     * checkout_time : 2016-07-15
     * closed : false
     * deleted : false
     * customer : 5
     * shipping_status : 0
     * hotelpackageordersnapshot : {"hotel_id":1,"house_id":1,"hotel_name":"酒店名字","house_name":"商务大床","front_price":130,"need_point":20}
     */

    private String number;
    private Object modified_by;
    private String created_on;
    private Object comment;
    private String modified_on;
    private String label;
    private String checkin_time;
    private String product;
    private int seller;
    private String uuid;
    private int process_state;
    private String request_notes;
    private int id;
    private int payment_status;
    private String checkout_time;
    private boolean closed;
    private boolean deleted;
    private int customer;
    private int shipping_status;
    /**
     * hotel_id : 1
     * house_id : 1
     * hotel_name : 酒店名字
     * house_name : 商务大床
     * front_price : 130
     * need_point : 20
     */

    private HotelpackageordersnapshotBean hotelpackageordersnapshot;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Object getModified_by() {
        return modified_by;
    }

    public void setModified_by(Object modified_by) {
        this.modified_by = modified_by;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public String getModified_on() {
        return modified_on;
    }

    public void setModified_on(String modified_on) {
        this.modified_on = modified_on;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(String checkin_time) {
        this.checkin_time = checkin_time;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getProcess_state() {
        return process_state;
    }

    public void setProcess_state(int process_state) {
        this.process_state = process_state;
    }

    public String getRequest_notes() {
        return request_notes;
    }

    public void setRequest_notes(String request_notes) {
        this.request_notes = request_notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }

    public String getCheckout_time() {
        return checkout_time;
    }

    public void setCheckout_time(String checkout_time) {
        this.checkout_time = checkout_time;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(int shipping_status) {
        this.shipping_status = shipping_status;
    }

    public HotelpackageordersnapshotBean getHotelpackageordersnapshot() {
        return hotelpackageordersnapshot;
    }

    public void setHotelpackageordersnapshot(HotelpackageordersnapshotBean hotelpackageordersnapshot) {
        this.hotelpackageordersnapshot = hotelpackageordersnapshot;
    }

    public static class HotelpackageordersnapshotBean {
        private int hotel_id;
        private int house_id;
        private String hotel_name;
        private String house_name;
        private int front_price;
        private int need_point;

        public int getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(int hotel_id) {
            this.hotel_id = hotel_id;
        }

        public int getHouse_id() {
            return house_id;
        }

        public void setHouse_id(int house_id) {
            this.house_id = house_id;
        }

        public String getHotel_name() {
            return hotel_name;
        }

        public void setHotel_name(String hotel_name) {
            this.hotel_name = hotel_name;
        }

        public String getHouse_name() {
            return house_name;
        }

        public void setHouse_name(String house_name) {
            this.house_name = house_name;
        }

        public int getFront_price() {
            return front_price;
        }

        public void setFront_price(int front_price) {
            this.front_price = front_price;
        }

        public int getNeed_point() {
            return need_point;
        }

        public void setNeed_point(int need_point) {
            this.need_point = need_point;
        }
    }
}
