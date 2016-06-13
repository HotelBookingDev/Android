package sf.hotel.com.hotel_client.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.avos.avoscloud.AVInstallation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.HomeActivity;
import sf.hotel.com.hotel_client.view.activity.LoginActivity;
import sf.hotel.com.hotel_client.view.activity.MainActivity;
import sf.hotel.com.hotel_client.view.interfaceview.ILoginView;
import sf.hotel.com.hotel_client.view.presenter.ILoginPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements ILoginView {

    @BindView(R.id.edit_name)
    EditText mEditName;
    @BindView(R.id.edit_pw)
    EditText mEditPw;

    ILoginPresenter mILoginPresenter;

    ClickListener mClickListener;

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
        return mEditName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEditPw.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEditName.setText("");
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
        return mEditName;
    }

    @Override
    public String getIntallationId() {
        return AVInstallation.getCurrentInstallation().getInstallationId();
    }

    @Override
    public void startHomeActivity() {
        mClickListener.startActivityByClass(HomeActivity.class);
    }

    @Override
    public void success(int type) {
        showViewToast(getContext().getResources().getString(R.string.login_success));
    }

    @Override
    public void failed(int type, Throwable e) {
        if (e instanceof APIException) {
            APIException exception = (APIException) e;

            int code = exception.getCode();
            if (code == Code.LOGIN_FORMAT_ERROR) {
                clearUserName();
                clearPassword();
            } else if (code == Code.LOGIN_NAME_NULL) {

            } else if (code == Code.LOGIN_PWD_NULL) {

            }
            showViewToast(exception.getErrorMessage(getBottomContext()));
        } else {
            showViewToast(e.getMessage());
        }

    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @OnClick(R.id.register_btn)
    public void register() {
        mClickListener.showFragment(LoginActivity.REGISTER);
    }

    public void setClickListener(ClickListener mClickListener) {
        this.mClickListener = mClickListener;
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
