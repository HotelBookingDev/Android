package sf.hotel.com.data.entity;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
//查询订单的的时候记录的对象和实际交易的订单无关
@DatabaseTable(tableName = "tb_localorder")
public class LocalOrder implements SearchSuggestion {
    public String getUserId() {
        return userId;
    }

    public long getFirstsearch_time() {
        return firstsearch_time;
    }

    public void setFirstsearch_time(long firstsearch_time) {
        this.firstsearch_time = firstsearch_time;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(String orderNum) {
        OrderNum = orderNum;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    @DatabaseField(generatedId = true, columnName = "firstsearch_time")
    private long firstsearch_time;

    @SerializedName("user_id")
    @DatabaseField(columnName = "user_id")
    private String userId;
    @SerializedName("order_id")
    @DatabaseField(columnName = "order_id")
    private String OrderNum;
    @DatabaseField(columnName = "update_time")
    @SerializedName("update_time")
    private long update_time;

    @Override
    public String getBody() {
        return OrderNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(OrderNum);
        dest.writeLong(update_time);
        dest.writeLong(firstsearch_time);
    }

    public static final Creator<LocalOrder> CREATOR = new Creator<LocalOrder>() {
        @Override
        public LocalOrder createFromParcel(Parcel in) {
            return new LocalOrder(in);
        }

        @Override
        public LocalOrder[] newArray(int size) {
            return new LocalOrder[size];
        }
    };

    public LocalOrder() {
    }

    public LocalOrder(Parcel source) {

        this.userId = source.readString();
        this.OrderNum = source.readString();
        this.update_time = source.readLong();
        this.firstsearch_time = source.readLong();
    }
}
