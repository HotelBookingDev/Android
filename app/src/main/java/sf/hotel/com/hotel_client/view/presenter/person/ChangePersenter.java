package sf.hotel.com.hotel_client.view.presenter.person;

import android.widget.EditText;

import sf.hotel.com.hotel_client.view.interfaceview.person.IChangePwdView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 */
public class ChangePersenter extends SuperPresenter {
    IChangePwdView mIChangePwdView;

    public ChangePersenter(IChangePwdView mIChangePwdView) {
        this.mIChangePwdView = mIChangePwdView;
    }

    public void changePwd() {
        EditText confimPwd = mIChangePwdView.getConfimPwd();
        EditText newPwd = mIChangePwdView.getNewPwd();
        EditText oldPwd = mIChangePwdView.getOldPwd();
        if (!confimPwd.getText().toString().equals(newPwd.getText().toString())) {
            mIChangePwdView.showViewToast("确认密码不相同");
        } else {
            //TODO 上传修改后的密码
        }
    }

    @Override
    public void destroy() {

    }
}
