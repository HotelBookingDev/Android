package sf.hotel.com.hotel_client.view.fragment.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.login.ILoginView;
import sf.hotel.com.hotel_client.view.presenter.login.ILoginPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements ILoginView {

    @BindView(R.id.et_phone)
    EditText mEditPhone;
    @BindView(R.id.edit_pw)
    EditText mEditPw;

    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    ILoginPresenter mILoginPresenter;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        mILoginPresenter = new ILoginPresenter(this);
        return view;
    }

    @Override
    public String getUserName() {
        return mEditPhone.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEditPw.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEditPhone.setText("");
    }

    @Override
    public void clearPassword() {
        mEditPw.setText("");
    }

    @Override
    public void startHomeActivity() {
        RxBus.getDefault().post(MessageFactory.createLoginMessage(LoginMessage.SHOW_MAIN));
    }

    @Override
    public void setEditPhone(String phone) {
        mEditPhone.setText(phone);
    }

    @Override
    public void setEditPwd(String pwd) {
        mEditPw.setText(pwd);
    }

    @Override
    public ImageView getAvatar() {
        return mIvAvatar;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mILoginPresenter.destroy();
    }

    @OnClick({R.id.login_btn, R.id.tv_just_look, R.id.register_btn})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.login_btn) {
            login();
        } else if (id == R.id.tv_just_look) {
            justLook();
        } else if (id == R.id.register_btn) {
            register();
        }
    }

    @Override
    public void login() {
        mILoginPresenter.login();
    }

    public void justLook() {
        startHomeActivity();
    }

    public void register() {
        RxBus.getDefault().post(MessageFactory.createLoginMessage(LoginMessage.SHOW_REGIST));
    }
}
