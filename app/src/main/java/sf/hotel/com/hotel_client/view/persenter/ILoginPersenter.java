package sf.hotel.com.hotel_client.view.persenter;

import android.content.Context;

import sf.hotel.com.data.eneity.StateEntity;
import sf.hotel.com.data.eneity.UserEntity;
import sf.hotel.com.data.interfaceeneity.ILoginEntity;
import sf.hotel.com.data.interfaceeneity.LoginEntityImp;
import sf.hotel.com.hotel_client.view.interfaceview.ILoginView;

/**
 * Created by FMT on 2016/6/3:18:54
 * EMAILE 1105896230@qq.com.
 */
public class ILoginPersenter implements Persenter {
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


    public void login() {
        mILoginView.showLoading();
        mILoginEntity.login(mILoginView.getUserName(), mILoginView.getPassword())
                .subscribe(this::check);
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
