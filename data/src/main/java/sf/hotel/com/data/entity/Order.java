package sf.hotel.com.data.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
@DatabaseTable(tableName = "tb_order")
public class Order {
    public static final int ALRADYORDER = 0x1;
    public static final int NOTORDER = 0x0;
    @DatabaseField(columnName = "user_id")
    @SerializedName("user_id")
    public long userId;

    public boolean isClosed() {
        return isClosed;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
//    订单号

    public long getOrder_num() {
        return order_num;
    }

    @DatabaseField(generatedId = true, columnName = "number")
    @SerializedName("number")
    private long order_num;
    //    订单的状态
    @DatabaseField(columnName = "payment_status")
    @SerializedName("payment_status")
    private int state;
    //    下单时间
    @DatabaseField(columnName = "time")
    @SerializedName("created_on")
    private String time;

    @DatabaseField(columnName = "closed")
    @SerializedName("closed")
    private boolean isClosed;
    @SerializedName("hotelpackageordersnapshot")
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Hotelshot snapshot;

    public Hotelshot getHotelShot() {
        return snapshot;
    }

    public int getState() {
        return state;
    }

    public String getTime() {
        return time;
    }

    public void setSnapshot(Hotelshot snapshot) {
        this.snapshot = snapshot;
    }

    public String getStateMessage() {
        String text = "";
        if (state == 1) {
            text = "订单当前在审核";
        } else if (state == 2) {
            text = "取消入住";
        }
        return text;
    }
}
