package sf.hotel.com.hotel_client.view.fragment.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.CaptchaText;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.login.IRegisterView;
import sf.hotel.com.hotel_client.view.presenter.login.IRegisterPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public class RegisterFragment extends BaseFragment implements IRegisterView {

    @BindView(R.id.et_phone)
    EditText editPhone;

    @BindView(R.id.edit_code)
    EditText editCode;

    @BindView(R.id.btn_regist_captcha)
    CaptchaText btnRegCaptcha;

    @BindView(R.id.btn_reg_submit)
    FancyButton btnRegSubmit;

    IRegisterPresenter mIRegisterPresenter;

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
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
    public void register() {
        mIRegisterPresenter.register();
    }

    @Override
    public void callPhoneCaptcha() {
        mIRegisterPresenter.sendCallCaptcha();
    }

    @Override
    public String getCaptcha() {
        return editCode.getText().toString();
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
    public void onDestroy() {
        super.onDestroy();
        mIRegisterPresenter.destroy();
    }

    @OnClick({
            R.id.btn_reg_submit, R.id.btn_regist_captcha, R.id.just_login_txt
    })
    void onClick(View view) {
        int id = view.getId();
        //修改是否接受消息
        if (id == R.id.btn_reg_submit) {
            //提交注册信息
            register();
        } else if (id == R.id.btn_regist_captcha) {
            //发送验证码
            callPhoneCaptcha();
        } else if (id == R.id.just_login_txt) {
            pop();
        }
    }


    @Override
    public String getPhoneNum() {
        return editPhone.getText().toString().trim();
    }

    @Override
    public void startHomeActivity() {
        start(new FillInfolationFragment());
    }
}
