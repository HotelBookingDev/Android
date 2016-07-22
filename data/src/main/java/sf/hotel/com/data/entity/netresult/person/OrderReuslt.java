package sf.hotel.com.data.entity.netresult.person;

import com.google.gson.annotations.SerializedName;

import sf.hotel.com.data.entity.Order;

/**
 * Created by "林其望".
 * DATE: 2016:07:21:20:46
 * email:1105896230@qq.com
 */

public class OrderReuslt {
    public Order getOrder() {
        return order;
    }

    @SerializedName("order")

    private Order order;

}
