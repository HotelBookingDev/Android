package sf.hotel.com.hotel_client.view.presenter.person;

import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.view.custom.ToggleButton;
import sf.hotel.com.hotel_client.view.interfaceview.person.ISettingView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/6/24
 * email: 1105896230@qq.com
 */
public class SettingPresenter extends SuperPresenter {
    ISettingView mSettingView;

    public SettingPresenter(ISettingView mSettingView) {
        super();
        this.mSettingView = mSettingView;
    }

    public void changeAcceptMsg() {
        //可能设置会是一个对象 只有一个接受消息 不考虑实体建立
        //TODO 做网络请求，保存本地 更新
        //直接做本地操作
        changeMsgSate();
    }

    private void changeMsgSate() {
        ToggleButton acceptMsg = mSettingView.getAcceptMsg();
        PreferencesUtils.saveAcceptMeg(mSettingView.getBottomContext(), !acceptMsg.isToggleOn());
        mSettingView.setSwitch(PreferencesUtils.getAcceptMeg(mSettingView.getBottomContext()),
                acceptMsg);
    }

    public void forgeoPwd() {
        if (checkIsLogin()) {
            mSettingView.toChangePwFragment();
        } else {
            mSettingView.showLoginActivity();
        }
    }
}
