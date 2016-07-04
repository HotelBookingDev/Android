package sf.hotel.com.data.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
@DatabaseTable(tableName = "tb_order")
public class Order {
    public static final int ALRADYORDER = 0x5;
    public static final int NOTORDER = 0x6;

    //    hotel_icon
    @DatabaseField(columnName = "hotel_url")
    private String hotel_url;
    //    hotel_name
    @DatabaseField(columnName = "hotel_name")
    private String hotel_name;
    //    酒店id
    @DatabaseField(columnName = "hotel_id")
    private String hotel_id;
    @DatabaseField(columnName = "room_name")
    private String room_name;
    //    订单号
    @DatabaseField(generatedId = true, columnName = "order_num")
    private String order_num;
    //    积分
    @DatabaseField(columnName = "point")
    private long point;
    //门市价
    @DatabaseField(columnName = "money")
    private String money;
    //    订单的状态
    @DatabaseField(columnName = "state")
    private int state;
    //    是否可以评论
    @DatabaseField(columnName = "isComment")
    private boolean isComment;
    //    下单时间
    @DatabaseField(columnName = "time")
    private long time;

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

    public long getTime() {
        return time;
    }
}
