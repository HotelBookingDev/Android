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
import sf.hotel.com.data.utils.HotelFileUtils;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.AndroidUtils;
import sf.hotel.com.hotel_client.utils.ToggleButton;
import sf.hotel.com.hotel_client.view.activity.LoginActivity;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.ISettingView;
import sf.hotel.com.hotel_client.view.presenter.person.SettingPresenter;

public class SettingFragment extends BaseFragment implements ISettingView {

    SettingPresenter mSettingPersnter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        initView();
        mSettingPersnter = new SettingPresenter(this);
        return view;
    }

    private void initView() {
        setBtnClearText();
        mEditionBtn.setText(
                mEditionBtn.getText() + "(" + AndroidUtils.getAppVersionName(getActivity()) + ")");

        setSwitch(PreferencesUtils.getAcceptMeg(getActivity()), mTbAcceptMsg);
    }

    public void loginOut() {
        //清空本地设置
        mSettingPersnter.loginOut();
    }

    public void aboutUs() {
        showToast("aboutUs");
    }

    public void starLoginActivtiy() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void settingclear() {
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
        mSettingPersnter.changeAcceptMsg();
    }

    @Override
    public void success(int type) {

    }

    @Override
    public void failed(int type, Throwable e) {

    }

    @OnClick({
            R.id.tb_accept_msg,
            R.id.setting_clear,
            R.id.piv_about_us,
            R.id.setting_out,
            R.id.piv_feed_back
    })
    void onClick(View view) {
        int id = view.getId();
        //修改是否接受消息
        if (id == R.id.tb_accept_msg) {
            sendMsg();
            //缓存清空
        } else if (id == R.id.setting_clear) {
            settingclear();
            //关于我们
        } else if (id == R.id.piv_about_us) {
            aboutUs();
            //退出
        } else if (id == R.id.setting_out) {
            loginOut();
            //意见反馈
        } else if (id == R.id.piv_feed_back) {
            feedBack();
        }
    }

    private void feedBack() {
        showToast("意见反馈");
    }
}
