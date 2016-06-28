package sf.hotel.com.hotel_client.view.interfaceview.person;

import android.content.Context;

import java.util.List;

import sf.hotel.com.data.entity.LocalOrder;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
public interface ISearchView {

    void showProgress();

    void dissProgress();

    Context getBottomContext();

    void showSearch(List<LocalOrder> localOrders);
}
