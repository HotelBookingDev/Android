package sf.hotel.com.hotel_client.view.presenter.person;

import sf.hotel.com.hotel_client.view.interfaceview.person.IAccountBalanceFragmentView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/6/30
 * email: 1105896230@qq.com
 */
public class AccountBalancePresenter extends SuperPresenter {
    IAccountBalanceFragmentView iAccountBalanceFragmentView;

    public AccountBalancePresenter(IAccountBalanceFragmentView iAccountBalanceFragmentView) {
        this.iAccountBalanceFragmentView = iAccountBalanceFragmentView;
    }

    @Override
    public void destroy() {

    }

    public void initViews() {
        //TODO 为网络请求
        iAccountBalanceFragmentView.showMoney("￥0.0");
    }
}
