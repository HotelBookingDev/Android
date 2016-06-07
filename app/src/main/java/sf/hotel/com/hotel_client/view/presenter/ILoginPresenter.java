package sf.hotel.com.hotel_client.view.presenter;


import android.widget.Toast;

import sf.hotel.com.data.entity.LoginResult;
import sf.hotel.com.data.entity.StateEntity;
import sf.hotel.com.data.entity.UserEntity;

import sf.hotel.com.data.interfaceeneity.ILoginEntity;
import sf.hotel.com.data.interfaceeneity.LoginEntityImp;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.net.login.LoginHelper;
import sf.hotel.com.hotel_client.view.interfaceview.ILoginView;

/**
 * Created by FMT on 2016/6/3:18:54
 * EMAILE 1105896230@qq.com.
 */
public class ILoginPresenter implements Presenter {
    private ILoginView mILoginView;
    private ILoginEntity mILoginEntity;

    public ILoginPresenter(ILoginView mILoginView) {
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

    public void login() {
//        mILoginView.showLoading();
//        mILoginEntity.login(mILoginView.getUserName(), mILoginView.getPassword())
//                .subscribe(this::check);

        LoginHelper.getInstance()
                .doLogin(mILoginView.getUserName(), mILoginView.getPassword())
                .subscribe(new SimpleSubscriber<LoginResult>(mILoginView.getContext(),
                        loginEntity -> {
                    //save...
                    Toast.makeText(mILoginView.getContext(), "loginSuccess", Toast.LENGTH_LONG).show();
                }));
    }

    private void check(StateEntity<UserEntity> userEntityStateEntity) {
        if (userEntityStateEntity.getCode() == 200) {
            mILoginEntity.update(userEntityStateEntity, mILoginView.getContext());
            mILoginView.success();
        } else if (userEntityStateEntity.getCode() == -1) {
            mILoginView.error();
        }
    }
}
