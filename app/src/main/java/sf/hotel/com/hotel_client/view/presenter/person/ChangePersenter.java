package sf.hotel.com.hotel_client.view.presenter.person;

import android.text.TextUtils;

import sf.hotel.com.data.interfaceeneity.person.IChagePwdImp;
import sf.hotel.com.data.interfaceeneity.person.IChangePwd;
import sf.hotel.com.data.utils.CheckUtils;
import sf.hotel.com.data.utils.StringUtils;
import sf.hotel.com.hotel_client.view.interfaceview.person.IChangePwdView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 */
public class ChangePersenter extends SuperPresenter {
    IChangePwdView mIChangePwdView;
    IChangePwd iChangePwd;

    public ChangePersenter(IChangePwdView mIChangePwdView) {
        this.mIChangePwdView = mIChangePwdView;
        iChangePwd = new IChagePwdImp();
    }

    public void changePwd() {
        String newPwd = mIChangePwdView.getNewPwd();
        String oldPwd = mIChangePwdView.getOldPwd();
        String confimPwd = mIChangePwdView.getConfimPwd();
        if (CheckUtils.isTextViewEmpty(newPwd) ||
                CheckUtils.isTextViewEmpty(oldPwd) ||
                CheckUtils.isTextViewEmpty(confimPwd)) {
            mIChangePwdView.showViewToast("密码不能为空");
        } else if (newPwd.length() < 6 || confimPwd.length() < 6) {
            mIChangePwdView.showViewToast("密码长度不能小于6位");
        } else if (!newPwd.equals(confimPwd)) {
            mIChangePwdView.showViewToast("密码不相等");
        } else {
            iChangePwd.chagngePwd(iChangePwd.getPhoneNum(), StringUtils.changePud(oldPwd),
                    StringUtils.changePud(newPwd)).subscribe(normalResult -> {
                iChangePwd.savePwd(mIChangePwdView.getBottomContext(),
                        StringUtils.changePud(newPwd));
                mIChangePwdView.showViewToast("修改成功");
            }, this::handlingException);
        }
    }

    @Override
    public void handlingException(Throwable e) {
        super.handlingException(e);
        if (e != null && !TextUtils.isEmpty(e.getMessage())) {
            mIChangePwdView.showViewToast(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
