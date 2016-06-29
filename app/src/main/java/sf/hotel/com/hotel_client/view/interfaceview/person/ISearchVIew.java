package sf.hotel.com.hotel_client.view.interfaceview.person;

import java.util.List;

import sf.hotel.com.data.entity.LocalOrder;
import sf.hotel.com.hotel_client.view.interfaceview.BaseView;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
public interface ISearchView extends BaseView {

    void showProgress();

    void dismissProgress();

    void showSearch(List<LocalOrder> localOrders);
}
