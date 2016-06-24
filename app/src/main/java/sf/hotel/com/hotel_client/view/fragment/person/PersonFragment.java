package sf.hotel.com.hotel_client.view.fragment.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.FragConstant;
import sf.hotel.com.hotel_client.view.activity.person.OrderActivity;
import sf.hotel.com.hotel_client.view.activity.person.SettingActivity;
import sf.hotel.com.hotel_client.view.activity.person.UserInfoActivity;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.LoginMessage;
import sf.hotel.com.hotel_client.view.event.hotel.Message;
import sf.hotel.com.hotel_client.view.event.hotel.PersonMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IPersonView;
import sf.hotel.com.hotel_client.view.presenter.person.PersonPersenter;

/**
 */
public class PersonFragment extends BaseFragment implements IPersonView {

    public static final int USERINFO = 0x1;
    public static final int SETTING = 0x2;
    public static final int ORDER = 0x3;

    PersonPersenter mPersonPersenter;

    public static PersonFragment newInstance() {

        Bundle args = new Bundle();

        PersonFragment fragment = new PersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        mPersonPersenter = new PersonPersenter(this);
        onRxEvent();
        return view;
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(Message.class)
                .subscribe(message -> {
                    if (message instanceof PersonMessage) {
                        switch (message.what) {
                            case PersonMessage.ORDER:
                                starFragment(FragConstant.ORDER);
                                getTopFragment();
                                break;
                        }
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    @OnClick(R.id.piv_money)
    public void clickMoney() {
        mPersonPersenter.clickMoney();
    }

    @OnClick(R.id.piv_order)
    public void clickOrder() {
        mPersonPersenter.clickOrder();
    }

    @OnClick(R.id.piv_person)
    public void clickPerson() {
        mPersonPersenter.clickPerson();
    }

    @OnClick(R.id.piv_evaluate)
    public void clickEvaluate() {
        mPersonPersenter.clickEvaluate();
    }

    @Override
    public void showLoginFragment() {
        RxBus.getDefault().post(new LoginMessage(LoginMessage.SHOW_LOGIN));
    }

    @Override
    public void showItemActivity(int type) {
        if (type == USERINFO) {
            Intent intent = new Intent(getActivity(), UserInfoActivity.class);
            startActivity(intent);
        } else if (type == ORDER) {
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            startActivity(intent);
        } else if (type == SETTING) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.piv_setting)
    public void clickSetting() {
        mPersonPersenter.clickSetting();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPersonPersenter.destroy();
    }
}