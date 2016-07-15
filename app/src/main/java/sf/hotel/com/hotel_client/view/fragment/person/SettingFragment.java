package sf.hotel.com.hotel_client.view.fragment.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sf.hotel.com.data.config.HotelConstant;
import sf.hotel.com.data.entity.WebViewsModel;
import sf.hotel.com.data.utils.HotelFileUtils;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.data.utils.WebViewModelFactory;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.AndroidUtils;
import sf.hotel.com.hotel_client.view.activity.WebViewActivity;
import sf.hotel.com.hotel_client.view.activity.register.LoginActivity;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.custom.ToggleButton;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.LoginMessage;
import sf.hotel.com.hotel_client.view.event.person.PersonMessage;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.ISettingView;
import sf.hotel.com.hotel_client.view.presenter.person.SettingPresenter;

public class SettingFragment extends BaseFragment implements ISettingView {

    SettingPresenter settingPresenter;

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.setting_clear)
    FancyButton mClearBtn;
    @BindView(R.id.setting_edition)
    FancyButton mEditionBtn;
    @BindView(R.id.tb_accept_msg)
    ToggleButton mTbAcceptMsg;

    @BindView(R.id.view_title)
    HotelTitleView view_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        initView();
        settingPresenter = new SettingPresenter(this);
        return view;
    }

    private void initView() {
        setBtnClearText();
        mEditionBtn.setText(
                mEditionBtn.getText() + "(" + AndroidUtils.getAppVersionName(getActivity()) + ")");

        setSwitch(PreferencesUtils.getAcceptMeg(getActivity()), mTbAcceptMsg);

        view_title.addLeftClick(v -> {
            getActivity().finish();
        });
    }

    public void loginOut() {
        //清空本地设置
        settingPresenter.loginOut();
    }

    public void aboutUs() {
        toWebView(WebViewModelFactory.getModel(WebViewModelFactory.BAIDU));
//        showViewToast("aboutUs");
    }

    public void logOutToLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void setting_clear() {
        HotelFileUtils.clearDiskCache();
        setBtnClearText();
    }

    @Override
    public void setSwitch(boolean isOpen, ToggleButton viewSwitch) {
        if (isOpen) {
            viewSwitch.setToggleOn(true);
        } else {
            viewSwitch.setToggleOff(false);
        }
    }

    private void toWebView(WebViewsModel model) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.KEY, model);
        startActivity(intent);
    }

    @Override
    public ToggleButton getAcceptMsg() {
        return mTbAcceptMsg;
    }

    private void setBtnClearText() {
        rx.Observable.just(mClearBtn)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(fancyButton -> HotelFileUtils.getSize(new File(HotelConstant.TEMP_IMG_DIR)))
                .filter(s -> s != null)
                .subscribe(s -> {
                    mClearBtn.setText(mClearBtn.getText() + "(" + s + ")");
                });
    }

    public void sendMsg() {
        settingPresenter.changeAcceptMsg();
    }

    @OnClick({
            R.id.tb_accept_msg,
            R.id.setting_clear,
            R.id.piv_about_us,
            R.id.setting_out,
            R.id.piv_feed_back,
            R.id.piv_safe
    })
    void onClick(View view) {
        int id = view.getId();
        //修改是否接受消息
        if (id == R.id.tb_accept_msg) {
            sendMsg();
            //缓存清空
        } else if (id == R.id.setting_clear) {
            setting_clear();
            //关于我们
        } else if (id == R.id.piv_about_us) {
            aboutUs();
            //退出
        } else if (id == R.id.setting_out) {
            loginOut();
            //意见反馈
        } else if (id == R.id.piv_feed_back) {
            feedBack();
        } else if (id == R.id.piv_safe) {
            settingPresenter.forgeoPwd();
        }
    }

    private void feedBack() {
        toWebView(WebViewModelFactory.getModel(WebViewModelFactory.BAIDU));
//        showViewToast("意见反馈");
    }

    @Override
    public void toChangePwFragment() {
        RxBus.getDefault().post(MessageFactory.createPersonMessage(PersonMessage.FORGORPW));
    }

    @Override
    public void showLoginActivity() {
        RxBus.getDefault().post(new LoginMessage(LoginMessage.SHOW_LOGIN));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        settingPresenter.destroy();
    }
}
