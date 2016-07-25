package sf.hotel.com.hotel_client.view.presenter.login;

import rx.Subscription;
import sf.hotel.com.data.interfaceeneity.login.IFillnfolationEntity;
import sf.hotel.com.data.interfaceeneity.login.IFillnfolationEntityImp;
import sf.hotel.com.data.utils.CheckUtils;
import sf.hotel.com.hotel_client.view.interfaceview.login.IFillnfolationView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by "林其望".
 * DATE: 2016:07:25:17:58
 * email:1105896230@qq.com
 */

public class FillInfolationPresenter extends SuperPresenter {
    IFillnfolationView view;
    IFillnfolationEntity entity;

    public FillInfolationPresenter(IFillnfolationView view) {
        this.view = view;
        entity = new IFillnfolationEntityImp();
    }

    public void submit() {
        String name = view.getName();
        int sex = view.getSex();
        String payPwd = view.getPayPwd();
        String configPayPwd = view.getConfigPayPwd();
        if (CheckUtils.isTextViewEmpty(name) || CheckUtils.isTextViewEmpty(payPwd) || CheckUtils.isTextViewEmpty(configPayPwd)) {
            view.showViewToast("信息请填写完整");
            return;
        } else if (payPwd.length() < 6 || configPayPwd.length() < 6) {
            view.showViewToast("密码不能太短");
        } else if (!payPwd.equals(configPayPwd)) {
            view.showViewToast("两个密码不匹配");
        } else {
//            submitInfo(name, sex, payPwd, configPayPwd);
        }
    }

    private void submitInfo(String name, int sex, String payPwd, String configPayPwd) {
        Subscription subscribe = entity.submit(name, payPwd, configPayPwd, sex).subscribe(normalResult -> {
            view.startHomeActivity();
        }, this::handlingException);
        addSubsrcicitpition(subscribe);
    }
}
