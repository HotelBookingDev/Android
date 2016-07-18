package sf.hotel.com.hotel_client.view.presenter.hotel;

import android.content.Context;

import rx.Subscription;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.interfaceeneity.CityEntity;
import sf.hotel.com.data.interfaceeneity.ICityEntityImp;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ICityView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ICityPresenter extends SuperPresenter {
    private ICityView mICityView;

    private CityEntity mICityEntityImp;

    public ICityPresenter(ICityView mICityView) {
        this.mICityView = mICityView;
        mICityEntityImp = new ICityEntityImp();
    }

    public ProvincesResult getProcincesResult(Context context) {
        return mICityEntityImp.getProcincesResult(context);
    }

    public CityBean getCityBean() {
        return mICityEntityImp.getCityBean(mICityView.getBottomContext());
    }

    public void callCityList() {
        Subscription subscribe = mICityEntityImp.callCityList()
                .subscribe(new SimpleSubscriber<ProvincesResult>(mICityView.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(ProvincesResult procincesResult) {
                        super.onNext(procincesResult);
                        mICityView.setCityAdapterList(procincesResult);
                    }
                });
        addSubsrcicitpition(subscribe);
    }

    public void saveSelectCity(CityBean cityBean) {
        mICityEntityImp.saveCitysBean(mICityView.getBottomContext(), cityBean);
    }
}
