package sf.hotel.com.hotel_client.view.fragment.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.avos.avoscloud.AVInstallation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.LoginMessage;
import sf.hotel.com.hotel_client.view.event.hotel.MessageFactory;
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
    public void showLoading() {
        showLog("showloadd");
    }

    @Override
    public void hideLoading() {
        showLog("hideLoading");
    }

    @Override
    public void showFailedError() {
        showLog("showFailedError");
    }

    @Override
    @OnClick(R.id.login_btn)
    public void login() {
        mILoginPresenter.login();
    }

    @Override
    public EditText getEditName() {
        return mEditPhone;
    }

    @Override
    public String getIntallationId() {
        return AVInstallation.getCurrentInstallation().getInstallationId();
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
    public void success(int type) {
    }

    @OnClick(R.id.tv_just_look)
    public void justLook() {
        startHomeActivity();
    }

    @Override
    public void failed(int type, Throwable e) {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @OnClick(R.id.register_btn)
    public void register() {
        RxBus.getDefault().post(MessageFactory.createLoginMessage(LoginMessage.SHOW_REGIST));
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void showViewToast(String msg) {
        showToast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mILoginPresenter.destroy();
    }
}