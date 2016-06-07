package sf.hotel.com.hotel_client.view.presenter;

import sf.hotel.com.data.entity.NormalResult;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.net.login.LoginHelper;
import sf.hotel.com.hotel_client.utils.TToast;
import sf.hotel.com.hotel_client.view.interfaceview.IRegisterView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public class IRegisterPresenter implements Presenter {
    IRegisterView mIRegisterView;

    public IRegisterPresenter(IRegisterView mIRegisterView) {
        this.mIRegisterView = mIRegisterView;
    }


    public void register(){
        LoginHelper.getInstance()
                .doRegister(mIRegisterView.getUName(), mIRegisterView.getPwd())
                .subscribe(new SimpleSubscriber<NormalResult>(mIRegisterView.getContext(),
                        normalResult -> TToast.showToast("register success")))
                ;
    }

    public void callPhoneCaptcha(){

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
}
