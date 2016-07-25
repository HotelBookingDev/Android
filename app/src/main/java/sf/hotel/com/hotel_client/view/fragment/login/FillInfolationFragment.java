package sf.hotel.com.hotel_client.view.fragment.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.login.IFillnfolationView;
import sf.hotel.com.hotel_client.view.presenter.login.FillInfolationPresenter;


public class FillInfolationFragment extends BaseFragment implements IFillnfolationView {


    public FillInfolationFragment() {
    }

    @BindView(R.id.et_name)
    EditText edName;
    @BindView(R.id.ed_pay_pwd)
    EditText edPayPwd;
    @BindView(R.id.ed_config_pay_pwd)
    EditText edConfigPayPwd;
    @BindView(R.id.rb_man)
    RadioButton radioButton;
    @BindView(R.id.view_title)
    HotelTitleView titleView;

    FillInfolationPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fill_infolation, container, false);
        ButterKnife.bind(this, view);
        presenter = new FillInfolationPresenter(this);
        titleView.addRightClick(v -> startHomeActivity());
        return view;
    }

    @OnClick({R.id.btn_reg_submit})
    public void submit() {
        presenter.submit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public int getSex() {
        return radioButton.isChecked() ? 1 : 0;
    }

    @Override
    public String getName() {
        return edName.getText().toString().trim();
    }

    @Override
    public String getPayPwd() {
        return edPayPwd.getText().toString();
    }

    @Override
    public String getConfigPayPwd() {
        return edConfigPayPwd.getText().toString();
    }

    @Override
    public void startHomeActivity() {
        RxBus.getDefault().post(MessageFactory.createLoginMessage(LoginMessage.SHOW_MAIN));
    }
}
