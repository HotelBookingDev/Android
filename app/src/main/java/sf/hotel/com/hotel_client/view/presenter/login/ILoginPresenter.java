package sf.hotel.com.hotel_client.view.presenter.login;

import android.text.TextUtils;

import rx.Subscription;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.interfaceeneity.login.ILoginEntity;
import sf.hotel.com.data.interfaceeneity.login.LoginEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.StringUtils;
import sf.hotel.com.hotel_client.view.interfaceview.login.ILoginView;

/**
 * Created by FMT on 2016/6/3:18:54
 * EMAILE 1105896230@qq.com.
 */
public class ILoginPresenter extends ILRcomPresenter {
    private ILoginView mILoginView;
    private ILoginEntity mILoginEntity;

    public ILoginPresenter(ILoginView mILoginView) {
        this.mILoginView = mILoginView;
        mILoginEntity = new LoginEntityImp();
        //初始化讲登录状态设置为false
        mILoginEntity.saveLogin(mILoginView.getBottomContext(), false);
        initView();
    }

    private void initView() {
        if (mILoginEntity.getPhone(mILoginView.getBottomContext()) != null &&
                mILoginEntity.getPwd(mILoginView.getBottomContext()) != null) {
            mILoginView.setEditPhone(mILoginEntity.getPhone(mILoginView.getBottomContext()));
            mILoginView.setEditPwd(mILoginEntity.getPwd(mILoginView.getBottomContext()));
        }
        initAvater(mILoginView.getBottomContext(), mILoginView.getAvatar());
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }



    @Override
    public void handlingException(Throwable e) {
        if (e instanceof APIException) {
            int i = ((APIException) e).getCode();
            int msgid = ((APIException) e).getMessageId();
            if (i == Code.LOGIN_FORMAT_ERROR) {
                mILoginView.clearUserName();
                mILoginView.clearPassword();
                mILoginView.showViewToast(getErrorString(msgid, mILoginView.getBottomContext()));
            } else if (i == Code.LOGIN_NAME_NULL) {
                mILoginView.showViewToast(getErrorString(msgid, mILoginView.getBottomContext()));
            } else if (i == Code.LOGIN_PWD_NULL) {
                mILoginView.showViewToast(getErrorString(msgid, mILoginView.getBottomContext()));
            } else if (i == Code.LOGIN_PWD_ERROR) {
                mILoginView.clearPassword();
                mILoginEntity.savePwd(mILoginView.getBottomContext(), null);
                if (msgid == 0) {
                    mILoginView.showViewToast(e.getMessage());
                }
            } else {
                if (msgid == 0) {
                    mILoginView.showViewToast(e.getMessage());
                }
            }
        } else {
            mILoginView.showViewToast(e.getMessage());
        }
    }

    public void login() {
        String pwd = getPostPwd(mILoginView.getPassword());
        Subscription subscribe = mILoginEntity.login(mILoginView.getUserName(), pwd)
                .subscribe(new SimpleSubscriber<LoginResult>(mILoginView.getBottomContext()) {
                    @Override
                    public void onNext(LoginResult loginResult) {
                        super.onNext(loginResult);
//                        登录和注册的父类方法
                        suceess(loginResult, mILoginEntity, mILoginView, pwd);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        handlingException(e);
                    }
                });
        addSubsrcicitpition(subscribe);
    }

    private String getPostPwd(String pwd) {
        pwd = StringUtils.changePud(pwd);
        if (!TextUtils.isEmpty(mILoginEntity.getPwd(mILoginView.getBottomContext()))) {
            if (mILoginEntity.getPwd(mILoginView.getBottomContext())
                    .equals(mILoginView.getPassword())) {
                pwd = mILoginView.getPassword();
            }
        }
        return pwd;
    }
}
