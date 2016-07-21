package sf.hotel.com.data.entity.netresult.person;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sf.hotel.com.data.entity.Order;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class OrderResult {
    @SerializedName("orders")
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }
}
