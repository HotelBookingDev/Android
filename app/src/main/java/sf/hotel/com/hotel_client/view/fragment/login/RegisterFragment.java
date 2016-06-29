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
import sf.hotel.com.hotel_client.view.custom.CaptchaButton;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.MessageFactory;
import sf.hotel.com.hotel_client.view.event.hotel.person.LoginMessage;
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
    EditText editRegUname;
    @BindView(R.id.et_password)
    EditText editRegPwd;

    @BindView(R.id.edit_reg_captcha)
    EditText editRegCaptcha;

    @BindView(R.id.btn_reg_captcha)
    CaptchaButton btnRegCaptcha;

    @BindView(R.id.btn_reg_submit)
    FancyButton btnRegSubmit;

    IRegisterPresenter mIRegisterPresenter;

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

    public void showLogin() {
        RxBus.getDefault().post(MessageFactory.createLoginMessage(LoginMessage.FRAGMENT_BACK));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIRegisterPresenter.destroy();
    }

    @OnClick({
            R.id.btn_reg_submit, R.id.btn_reg_captcha
    })
    void onClick(View view) {
        int id = view.getId();
        //修改是否接受消息
        if (id == R.id.btn_reg_submit) {
            //提交注册信息
            register();
        } else if (id == R.id.btn_reg_captcha) {
            //发送验证码
            callPhoneCaptcha();
        }
    }
}
