package sf.hotel.com.data.entity.netresult.person;

import com.google.gson.annotations.SerializedName;

import sf.hotel.com.data.entity.OrderManager;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class OrderManagerResult {
    @SerializedName("orderMangaer")
    private OrderManager manager;

    public OrderManager getManager() {
        return manager;
    }
}
