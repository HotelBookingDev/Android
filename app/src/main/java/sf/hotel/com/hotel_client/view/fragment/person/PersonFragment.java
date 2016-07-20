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
import sf.hotel.com.hotel_client.view.activity.person.CampaignInfoActivity;
import sf.hotel.com.hotel_client.view.activity.person.EvalueActivity;
import sf.hotel.com.hotel_client.view.activity.person.InvoiceActivity;
import sf.hotel.com.hotel_client.view.activity.person.MoneyActivity;
import sf.hotel.com.hotel_client.view.activity.person.OrderActivity;
import sf.hotel.com.hotel_client.view.activity.person.SettingActivity;
import sf.hotel.com.hotel_client.view.activity.person.UserInfoActivity;
import sf.hotel.com.hotel_client.view.event.Message;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.event.person.PersonMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IPersonView;
import sf.hotel.com.hotel_client.view.presenter.person.PersonPresenter;

/**
 */
public class PersonFragment extends BaseFragment implements IPersonView {

    public static final int USERINFO = 0x1;
    public static final int SETTING = 0x2;
    public static final int ORDER = 0x3;
    public static final int MONEY = 0x4;
    public static final int EVALUATE = 0x5;
    public static final int INVOICE = 0x6;
    public static final int INFO = 0x7;
    PersonPresenter mPersonPresenter;

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
        mPersonPresenter = new PersonPresenter(this);
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
                                start(OrderFragment.newInstance());
                                break;
                        }
                    }
                });
        addSubscription(subscribe);
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
        } else if (type == MONEY) {
            Intent intent = new Intent(getActivity(), MoneyActivity.class);
            startActivity(intent);
        } else if (type == EVALUATE) {
            Intent intent = new Intent(getActivity(), EvalueActivity.class);
            startActivity(intent);
        } else if (type == INVOICE) {
            Intent intent = new Intent(getActivity(), InvoiceActivity.class);
            startActivity(intent);
        } else if (type == INFO) {
            Intent intent = new Intent(getActivity(), CampaignInfoActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void clickMyInvoice() {
        mPersonPresenter.clicInvoice();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPersonPresenter.destroy();
    }

    @OnClick({R.id.ln_my_money, R.id.ln_order, R.id.piv_person, R.id.piv_setting, R.id.piv_help, R.id.piv_about_us, R.id.piv_invoice, R.id.iv_clock})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ln_my_money) {
            clickMoney();
        } else if (id == R.id.ln_order) {
            clickOrder();
        } else if (id == R.id.piv_person) {
            clickPerson();
        } else if (id == R.id.piv_setting) {
            clickSetting();
        } else if (id == R.id.piv_invoice) {
            clickMyInvoice();
        } else if (id == R.id.iv_clock) {
            clickInfo();
        }
    }

    private void clickInfo() {
        mPersonPresenter.clickInfo();
    }

    public void clickMoney() {
        mPersonPresenter.clickMoney();
    }

    public void clickOrder() {
        mPersonPresenter.clickOrder();
    }

    public void clickPerson() {
        mPersonPresenter.clickPerson();
    }


    public void clickSetting() {
        mPersonPresenter.clickSetting();
    }
}
