package sf.hotel.com.data.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 林其望
 * data：2016/7/5
 * email: 1105896230@qq.com
 */
//酒店快照
@DatabaseTable(tableName = "tb_hotel_shot")
public class Hotelshot {
    @SerializedName("id")
    @DatabaseField(id = true, unique = true, columnName = "id")
    private long id;
    @DatabaseField(columnName = "hotel_id")
    @SerializedName("hotel_id")
    private long hotel_id;
    @DatabaseField(columnName = "house_id")
    @SerializedName("house_id")
    private long house_id;

    public void setId(long id) {
        this.id = id;
    }

    public long getNeed_point() {
        return need_point;
    }

    public long getId() {
        return id;
    }

    public long getHotel_id() {
        return hotel_id;
    }

    public long getHouse_id() {
        return house_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getHouse_name() {
        return house_name;
    }

    public long getFron_price() {
        return fron_price;
    }

    @DatabaseField(columnName = "hotel_name")

    @SerializedName("hotel_name")
    private String hotel_name;
    @DatabaseField(columnName = "house_name")
    @SerializedName("house_name")
    private String house_name;
    @DatabaseField(columnName = "fron_price")
    @SerializedName("fron_price")
    private long fron_price;
    @DatabaseField(columnName = "need_point")
    @SerializedName("need_point")
    private long need_point;
}
