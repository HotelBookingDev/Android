package sf.hotel.com.data.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 林其望
 * data：2016/7/5
 * email: 1105896230@qq.com
 */
@DatabaseTable(tableName = "tb_order_hotel")
public class OrderAndHotel {

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Order mOrder;

    public Hotelshot getmHotelshot() {
        return mHotelshot;
    }

    public void setmHotelshot(Hotelshot mHotelshot) {
        this.mHotelshot = mHotelshot;
    }

    public Order getmOrder() {
        return mOrder;
    }

    public void setmOrder(Order mOrder) {
        this.mOrder = mOrder;
    }

    @DatabaseField(foreign = true, foreignAutoRefresh = true)

    private Hotelshot mHotelshot;

    @DatabaseField(columnName = "user_id")
    private long user_id;
    @DatabaseField(columnName = "state")
    private int state;

    //    订单号
    @DatabaseField(columnName = "num", generatedId = true)
    private long num;
    @DatabaseField(columnName = "time")
    private long time;

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
