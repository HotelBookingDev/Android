package sf.hotel.com.hotel_client.view.presenter.person;

import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.hotel_client.view.activity.FragConstant;
import sf.hotel.com.hotel_client.view.interfaceview.person.IPersonView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望 on 2016/6/16.
 */
public class PersonPersenter extends SuperPresenter {
    IPersonView mIPersonView;

    public PersonPersenter(IPersonView mIPersonView) {
        this.mIPersonView = mIPersonView;
    }

    public void clickMoney() {
        if (checkIsLogin()) {

        } else {
            mIPersonView.showLoginFragment();
        }
    }

    public void clickOrder() {
        if (checkIsLogin()) {
            mIPersonView.showItemFragment(FragConstant.ORDER);
        } else {
            mIPersonView.showLoginFragment();
        }
    }

    public void clickEvaluate() {
        if (checkIsLogin()) {

        } else {
            mIPersonView.showLoginFragment();
        }
    }

    private boolean checkIsLogin() {
        return EntityContext.getInstance().getmCurrentUser() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    //TODO 这个界面会进行网络请求在界面销毁记得要把网络cancle了
    @Override
    public void destroy() {

    }

    public void clickPerson() {
        if (checkIsLogin()) {
            mIPersonView.showItemFragment(FragConstant.ORDER);
        } else {
            mIPersonView.showLoginFragment();
        }
    }
}
