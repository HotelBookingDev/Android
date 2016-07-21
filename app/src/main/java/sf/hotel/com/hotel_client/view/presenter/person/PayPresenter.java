package sf.hotel.com.hotel_client.view.presenter.person;

import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.hotel_client.alipay.PayCallBack;
import sf.hotel.com.hotel_client.alipay.PayHelper;
import sf.hotel.com.hotel_client.alipay.Result;
import sf.hotel.com.hotel_client.view.custom.PayView;
import sf.hotel.com.hotel_client.view.interfaceview.person.IPayView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by "林其望".
 * DATE: 2016:07:21:9:53
 * email:1105896230@qq.com
 */

public abstract class PayPresenter extends SuperPresenter implements PayView.PayClick, PayCallBack {
    //   点击充值的回掉事件
    IPayView view;

    public PayPresenter(IPayView view) {
        this.view = view;
    }

    @Override
    public void Click(String money, int postion) {
        boolean conitue = isConitue(money);
        if (!conitue) return;
        addMoney(money, postion);
    }

    private void addMoney(String money, int postion) {
        if (postion == PayView.PAYALIAL) {
            aliPay(money);
        } else if (postion == PayView.PAYWECHAT) {

        }
    }

    private boolean isConitue(String money) {
        if (money.equals("")) {
            view.showViewToast("请输入金额");
            return false;
        }
        Long moenys = Long.parseLong(money);
        if (moenys % 100 != 0) {
            view.showViewToast("请输入100的倍数");
            return false;
        }
        return true;
    }


    private void aliPay(String money) {
        ApiWrapper.getInstance().callPay(money).subscribe(payResult -> {
            PayHelper.getInstance().pay(view.getActivity(), payResult.getUrl(), this);
        });
    }

    public void showPayView() {
        view.showPayView(this);
    }

    public void dimissPayView() {
        view.dissMissPayView();
    }
}
