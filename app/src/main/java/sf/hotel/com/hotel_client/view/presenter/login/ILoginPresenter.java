package sf.hotel.com.hotel_client.view.presenter.login;

import android.text.TextUtils;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.entity.netresult.LoginResult;
import sf.hotel.com.data.entity.netresult.NormalResult;
import sf.hotel.com.data.interfaceeneity.person.ILoginEntity;
import sf.hotel.com.data.interfaceeneity.person.LoginEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.Exception.Code;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.StringUtils;
import sf.hotel.com.hotel_client.view.interfaceview.login.ILoginView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by FMT on 2016/6/3:18:54
 * EMAILE 1105896230@qq.com.
 */
public class ILoginPresenter extends SuperPresenter {
    private ILoginView mILoginView;
    private ILoginEntity mILoginEntity;

    private CompositeSubscription mCompositeSubscription;

    public ILoginPresenter(ILoginView mILoginView) {
        this.mILoginView = mILoginView;
        mILoginEntity = new LoginEntityImp();
        mCompositeSubscription = new CompositeSubscription();
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
    public void destroy() {
        mCompositeSubscription.unsubscribe();
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
                        postIntallationId();
                        //保存用户信息
                        Observable.just(loginResult)
                                .filter(loginResult1 -> loginResult1 ==
                                        null ? Boolean.FALSE : Boolean.TRUE)
                                .filter(loginResult1 -> loginResult1.getUserEntity() ==
                                        null ? Boolean.FALSE : Boolean.TRUE)
                                .doOnNext(
                                        loginResult1 -> saveUserInfo(mILoginView.getUserName(), pwd,
                                                loginResult.getUserEntity().getAvatar(),
                                                String.valueOf(
                                                        loginResult.getUserEntity().getUserId())))
                                .doOnNext(loginResult1 -> mILoginEntity.upDateUserInfo(
                                        mILoginView.getBottomContext(),
                                        loginResult1.getUserEntity()))
                                .doOnNext(loginResult1 -> EntityContext.getInstance()
                                        .setmCurrentUser(loginResult1.getUserEntity()))
                                .subscribe(loginResult1 -> {
                                    mILoginView.startHomeActivity();
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        handlingException(e);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    private String getPostPwd(String pwd) {
        pwd = StringUtils.md5(pwd);
        if (!TextUtils.isEmpty(mILoginEntity.getPwd(mILoginView.getBottomContext()))) {
            if (mILoginEntity.getPwd(mILoginView.getBottomContext())
                    .equals(mILoginView.getPassword())) {
                pwd = mILoginView.getPassword();
            }
        }
        return pwd;
    }

    private void postIntallationId() {
        Subscription subscribe = mILoginEntity.postInllation("android", mILoginView.getUserName(),
                mILoginView.getIntallationId())
                .subscribe(new SimpleSubscriber<NormalResult>(mILoginView.getBottomContext()) {
                    @Override
                    public void onNext(NormalResult loginResult) {
                        super.onNext(loginResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    private void saveUserInfo(String phone, String pwd, String avatar, String id) {
        mILoginEntity.savePhone(mILoginView.getBottomContext(), phone);
        mILoginEntity.savePwd(mILoginView.getBottomContext(), pwd);
        mILoginEntity.saveAvatar(mILoginView.getBottomContext(), avatar);
        mILoginEntity.saveUserId(mILoginView.getBottomContext(), id);
    }
}
