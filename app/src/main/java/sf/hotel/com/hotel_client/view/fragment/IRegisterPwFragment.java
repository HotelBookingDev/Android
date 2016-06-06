package sf.hotel.com.hotel_client.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.interfaceview.IRegiserPwView;
import sf.hotel.com.hotel_client.view.persenter.IRegisterPwPersenter;

/**
 * Created by FMT on 2016/6/6:10:15
 * EMAILE 1105896230@qq.com.
 */
public class IRegisterPwFragment extends BaseFragment implements IRegiserPwView {

    IRegisterPwPersenter mIRegisterPwPersenter;
    @BindView(R.id.register_phone)
    EditText mEdPhone;
    @BindView(R.id.register_send)
    Button mSendBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registe_pwr, container, false);
        ButterKnife.bind(this, view);
        mIRegisterPwPersenter = new IRegisterPwPersenter(this);
        return view;
    }

    @Override
    public String getPhoneNum() {
        return mEdPhone.getText().toString();
    }

    @OnClick(R.id.register_send)
    @Override
    public void sendInvitationCode() {
        mIRegisterPwPersenter.sendMessage();
    }

    @Override
    public void setInvitationCodeNum(String num) {
        mSendBtn.setText(num);
    }

    @Override
    public void reset() {
        mSendBtn.setText(R.string.get_code);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
