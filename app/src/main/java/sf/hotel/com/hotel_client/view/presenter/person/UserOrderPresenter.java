package sf.hotel.com.hotel_client.view.presenter.person;

import sf.hotel.com.data.interfaceeneity.person.IOrder;
import sf.hotel.com.data.interfaceeneity.person.IOrderImp;
import sf.hotel.com.hotel_client.view.interfaceview.person.IUserOrderView;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class UserOrderPresenter {
    IUserOrderView mIUserOrderView;
    IOrder mIorder;

    public UserOrderPresenter(IUserOrderView mIUserOrderView) {
        this.mIUserOrderView = mIUserOrderView;
        mIorder = new IOrderImp();
    }

    public void getDatas(int position) {
        getDb(position);
    }

    private void getDb(int position) {
        mIorder.getOrderByDb(mIUserOrderView.getBottomContext(), position).subscribe(orders -> {
            if (orders != null) {
                mIUserOrderView.showOrder(orders);
            }
        });
    }
}
