package sf.hotel.com.hotel_client.view.presenter.person;

import sf.hotel.com.hotel_client.view.fragment.person.PersonFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IPersonView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/6/16
 * email: 1105896230@qq.com
 */
public class PersonPresenter extends SuperPresenter {
    IPersonView mIPersonView;

    public PersonPresenter(IPersonView mIPersonView) {
        this.mIPersonView = mIPersonView;
    }

    public void clickMoney() {
        if (checkIsLogin()) {
            mIPersonView.showItemActivity(PersonFragment.MONEY);
        } else {
            mIPersonView.showLoginFragment();
        }
    }

    public void clickOrder() {
        if (checkIsLogin()) {
            mIPersonView.showItemActivity(PersonFragment.ORDER);
        } else {
            mIPersonView.showLoginFragment();
        }
    }

    public void clickEvaluate() {
        if (checkIsLogin()) {
            mIPersonView.showItemActivity(PersonFragment.EVALUATE);
        } else {
            mIPersonView.showLoginFragment();
        }
    }

    //TODO 这个界面会进行网络请求在界面销毁记得要把网络cancel了
    @Override
    public void destroy() {

    }

    public void clickPerson() {
        if (checkIsLogin()) {
            mIPersonView.showItemActivity(PersonFragment.USERINFO);
        } else {
            mIPersonView.showLoginFragment();
        }
    }

    public void clickSetting() {
        mIPersonView.showItemActivity(PersonFragment.SETTING);
    }
}
