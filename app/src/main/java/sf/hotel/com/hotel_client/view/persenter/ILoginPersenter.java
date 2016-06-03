package sf.hotel.com.hotel_client.view.persenter;

import sf.hotel.com.data.interfaceeneity.ILoginEntity;
import sf.hotel.com.data.interfaceeneity.LoginEntityImp;
import sf.hotel.com.data.interfaceeneity.onLoginLinstener;
import sf.hotel.com.hotel_client.Utils.CheckUtils;
import sf.hotel.com.hotel_client.view.interfaceview.ILoginView;

/**
 * Created by FMT on 2016/6/3:18:54
 * EMAILE 1105896230@qq.com.
 */
public class ILoginPersenter implements Persenter, onLoginLinstener {
    private ILoginView mILoginView;
    private ILoginEntity mILoginEntity;

    public ILoginPersenter(ILoginView mILoginView) {
        this.mILoginView = mILoginView;
        mILoginEntity = new LoginEntityImp();
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    public void getUserInfo() {
        mILoginView.showLoading();
        rx.Observable.just(mILoginView).filter(iLoginView -> {
            boolean isPhont = CheckUtils.checkPhoneTextView(mILoginView.getEditName());
            if (!isPhont) {
                mILoginView.showFailedError();
            }
            return isPhont;
        }).subscribe(iLoginView -> {
            mILoginEntity.login(mILoginView.getUserName(), mILoginView.getPassword(),
                    ILoginPersenter.this);
        });
    }

    @Override
    public void success() {
        mILoginView.success();
        mILoginView.hideLoading();
    }

    @Override
    public void error() {
        mILoginView.error();
        mILoginView.hideLoading();
    }
}
