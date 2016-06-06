package sf.hotel.com.hotel_client.view.persenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import sf.hotel.com.hotel_client.view.interfaceview.IRegiserPwView;

/**
 * Created by FMT on 2016/6/6:10:44
 * EMAILE 1105896230@qq.com.
 */
public class IRegisterPwPersenter implements Persenter {

    private IRegiserPwView mIRegisterPwView;
    private final int TIME = 60;

    private boolean isFilter = false;

    @Override
    public void resume() {

    }

    public IRegisterPwPersenter(IRegiserPwView mIRegisterPwView) {
        this.mIRegisterPwView = mIRegisterPwView;
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void sendMessage() {
        String phoneNum = mIRegisterPwView.getPhoneNum();
        if (isFilter) return;
        isFilter = true;
        Observable.just(phoneNum)
                .interval(0, 1, TimeUnit.SECONDS)
                .limit(TIME)
                .map(aLong -> TIME - aLong)
                .subscribe(aLong -> {
                    chagemIRegisterPwView(aLong);
                });
    }

    private void chagemIRegisterPwView(long aLong) {
        if (aLong == 1) {
            isFilter = false;
            mIRegisterPwView.reset();
        } else {
            mIRegisterPwView.setInvitationCodeNum(aLong + "");
        }
    }
}
