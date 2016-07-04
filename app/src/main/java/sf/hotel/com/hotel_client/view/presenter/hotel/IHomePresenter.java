package sf.hotel.com.hotel_client.view.presenter.hotel;

import android.text.TextUtils;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.interfaceeneity.hotel.HomeEntity;
import sf.hotel.com.data.interfaceeneity.hotel.IHomeEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IHomeContainerView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * author MZ
 * email sanfenruxi1@163.com
 * date 16/6/29.
 */
public class IHomePresenter extends SuperPresenter {

    IHomeContainerView iHomeContainerView;

    HomeEntity mEntityImp;

    CompositeSubscription mCompositeSubscription;

    public IHomePresenter(IHomeContainerView iHomeContainerView) {
        this.iHomeContainerView = iHomeContainerView;
        mEntityImp = new IHomeEntityImp();
        mCompositeSubscription = new CompositeSubscription();
    }

    public void callCityList() {
        Subscription subscribe = mEntityImp.callCityList()
                .subscribe(new Action1<ProvincesResult>() {
                    @Override
                    public void call(ProvincesResult provincesResult) {
                        onSuccessNext(provincesResult);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //handlingException(throwable);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    public void loadCitysBeanCache() {
        CityBean cityBean = HotelDao.getCitysBean(
                iHomeContainerView.getBottomContext());
        if (cityBean != null && !TextUtils.isEmpty(cityBean.getName())) {
            iHomeContainerView.setTextCityName(cityBean.getName());
            iHomeContainerView.setCityBean(cityBean);
        } else {
            String cityCode = PreferencesUtils.getCityCode(iHomeContainerView.getBottomContext());
            String cityName = PreferencesUtils.getCityName(iHomeContainerView.getBottomContext());
            if (cityCode != null && cityName != null) {
                iHomeContainerView.setTextCityName(cityName);
                cityBean = new CityBean();
                cityBean.setName(cityName);
                cityBean.setId(Integer.valueOf(cityCode));
                iHomeContainerView.setCityBean(cityBean);
            }
        }
    }

    public void saveCityBean(CityBean cityBean) {
        HotelDao.saveCitysBean(iHomeContainerView.getBottomContext(), cityBean);
    }

    public CityBean getCityBean(){
        return HotelDao.getCitysBean(iHomeContainerView.getBottomContext());
    }

    public void handlingException(Throwable e) {
        if (e instanceof APIException) {
            iHomeContainerView.showViewToast(e.getMessage());
        } else {
            iHomeContainerView.showViewToast(e.getMessage());
        }
    }

    public void onSuccessNext(ProvincesResult provincesResult) {
        HotelDao.saveProcincesResult(iHomeContainerView.getBottomContext(), provincesResult);
    }

    @Override
    public void destroy() {
        if (mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
