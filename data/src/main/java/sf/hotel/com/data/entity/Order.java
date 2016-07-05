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

    //    订单号

    public long getOrder_num() {
        return order_num;
    }

    public long getId() {
        return id;
    }

    @DatabaseField(generatedId = true, columnName = "order_num")
    @SerializedName("number")
    private long order_num;
    //    订单的状态
    @DatabaseField(columnName = "state")
    @SerializedName("payment_status")
    private int state;
    //    下单时间
    @DatabaseField(columnName = "time")
    @SerializedName("created_on")
    private long time;
    @DatabaseField(columnName = "user_id")
    @SerializedName("id")
    private long id;

    @SerializedName("snapshot")
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "id")
    private Hotelshot snapshot;

    public Hotelshot getHotelShot() {
        return snapshot;
    }

    public int getState() {
        return state;
    }

    public long getTime() {
        return time;
    }

    public void setSnapshot(Hotelshot snapshot) {
        this.snapshot = snapshot;
    }
}
