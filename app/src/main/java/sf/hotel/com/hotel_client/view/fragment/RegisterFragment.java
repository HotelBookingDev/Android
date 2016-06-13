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
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.LoginActivity;
import sf.hotel.com.hotel_client.view.custom.CaptchaButton;
import sf.hotel.com.hotel_client.view.interfaceview.ICallBack;
import sf.hotel.com.hotel_client.view.interfaceview.IRegisterView;
import sf.hotel.com.hotel_client.view.presenter.IRegisterPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public class RegisterFragment extends BaseFragment implements IRegisterView {

    @BindView(R.id.edit_reg_uname)
    EditText editRegUname;
    @BindView(R.id.edit_reg_pwd)
    EditText editRegPwd;

    @BindView(R.id.edit_reg_captcha)
    EditText editRegCaptcha;

    @BindView(R.id.btn_reg_captcha)
    CaptchaButton btnRegCaptcha;

    @BindView(R.id.btn_reg_submit)
    Button btnRegSubmit;

    IRegisterPresenter mIRegisterPresenter;



    ClickListener mClickListener;


    public void setClickListener(ClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        mIRegisterPresenter = new IRegisterPresenter(this);
        return view;
    }

    @Override
    @OnClick(R.id.btn_reg_submit)
    public void register() {
        mIRegisterPresenter.register();
    }

    @Override
    @OnClick(R.id.btn_reg_captcha)
    public void callPhoneCaptcha() {
        mIRegisterPresenter.callPhoneCaptcha();
    }

    @Override
    public String getUName() {
        return editRegUname.getText().toString();
    }

    @Override
    public String getPwd() {
        return editRegPwd.getText().toString();
    }

    @Override
    public String getCaptcha() {
        return editRegCaptcha.getText().toString();
    }

    @Override
    public void startTimer() {
        btnRegCaptcha.startTimer();
    }

    @Override
    public Context getBottomContext() {
        return getActivity();
    }

    @Override
    public void success(int type) {
        switch (type){
            case ICallBack.REGISTER:
                showViewToast("注册成功");
                showLogin();
                break;
            case ICallBack.SMS_CODE:
                showViewToast("获取验证码成功");
                break;
        }
    }

    @Override
    public void failed(int type, Throwable e) {
        if (e instanceof APIException){
            APIException exception = (APIException) e;
            switch (type){
                case ICallBack.REGISTER:
                    showViewToast(exception.getErrorMessage(getBottomContext()));
                    break;
                case ICallBack.SMS_CODE:
                    showViewToast(exception.getErrorMessage(getBottomContext()));
                    break;
            }
        }else {
            showViewToast(e.getMessage());
        }
    }

    public void showLogin(){
        mClickListener.onFragmentBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIRegisterPresenter.destroy();
    }

    @Override
    public void showViewToast(String msg) {
        showToast(msg);
    }
}
