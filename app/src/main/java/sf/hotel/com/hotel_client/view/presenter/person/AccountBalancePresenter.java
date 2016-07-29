package sf.hotel.com.hotel_client.view.presenter.person;

import rx.functions.Action1;
import sf.hotel.com.data.entity.netresult.pay.PayResult;
import sf.hotel.com.data.interfaceeneity.person.IAccountBalanceEntity;
import sf.hotel.com.data.interfaceeneity.person.IAccountBalanceEntityImp;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.alipay.PayCallBack;
import sf.hotel.com.hotel_client.alipay.PayHelper;
import sf.hotel.com.hotel_client.alipay.Result;
import sf.hotel.com.hotel_client.view.activity.pay.PayActivity;
import sf.hotel.com.hotel_client.view.interfaceview.person.IAccountBalanceFragmentView;

/**
 * Created by 林其望
 * data：2016/6/30
 * email: 1105896230@qq.com
 */
public class AccountBalancePresenter extends PayPresenter {
    IAccountBalanceFragmentView iAccountBalanceFragmentView;
    IAccountBalanceEntity entity;

    public AccountBalancePresenter(IAccountBalanceFragmentView iAccountBalanceFragmentView) {
        super(iAccountBalanceFragmentView);
        this.iAccountBalanceFragmentView = iAccountBalanceFragmentView;
        entity = new IAccountBalanceEntityImp();
    }


    public void initViews() {
        //TODO 为网络请求
        iAccountBalanceFragmentView.showMoney(entity.getPoint() + ".00");
    }


    @Override
    public void success(Result result) {
        view.showViewToast("充值成功");
    }

    @Override
    public void failed(Result result) {
        view.showViewToast("充值失败");
    }
}
