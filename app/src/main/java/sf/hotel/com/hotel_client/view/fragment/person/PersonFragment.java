package sf.hotel.com.hotel_client.view.fragment.person;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.LoginMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IPersonView;
import sf.hotel.com.hotel_client.view.presenter.person.PersonPersenter;

/**
 */
public class PersonFragment extends BaseFragment implements IPersonView {
    PersonPersenter mPersonPersenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        mPersonPersenter = new PersonPersenter(this);
        return view;
    }

    @OnClick(R.id.piv_money)
    public void myMoney() {
        mPersonPersenter.myMoney();
    }

    @OnClick(R.id.piv_order)
    public void myOrder() {
        mPersonPersenter.myOrder();
    }

    @OnClick(R.id.piv_evaluate)
    public void myEvaluate() {
        mPersonPersenter.myEvaluate();
    }

    @Override
    public void showLoginFragment() {
        RxBus.getDefault().post(new LoginMessage(LoginMessage.SHOW_LOGIN));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPersonPersenter.destroy();
    }
}
