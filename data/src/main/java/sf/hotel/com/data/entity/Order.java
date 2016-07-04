package sf.hotel.com.data.entity;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class Order {
    //    hotel_icon
    private String hotel_url;
    //    hotel_name
    private String hotel_name;
    //    酒店id
    private String hotel_id;

    private String room_name;
    //    订单号
    private String order_num;
    //    积分
    private long point;
    //门市价
    private String money;
    //    订单的状态
    private int state;
    //    是否可以评论
    private boolean isComment;

    public String getHotel_url() {
        return hotel_url;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public String getOrder_num() {
        return order_num;
    }

    public long getPoint() {
        return point;
    }

    public String getMoney() {
        return money;
    }

    public int getState() {
        return state;
    }

    public boolean isComment() {
        return isComment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //    下单时间
    private String time;
}
