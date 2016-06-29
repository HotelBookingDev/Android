package sf.hotel.com.hotel_client.view.presenter.hotel;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.data.interfaceeneity.hotel.IHomeEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.view.fragment.HomeContainer;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/29.
 */
public class IHomePresenter  extends SuperPresenter{


    HomeContainer mHomeContainer;

    IHomeEntityImp mEntityImp;

    CompositeSubscription mCompositeSubscription;

    public IHomePresenter(HomeContainer mHomeContainer) {
        this.mHomeContainer = mHomeContainer;
        mEntityImp = new IHomeEntityImp();
        mCompositeSubscription = new CompositeSubscription();
    }


    public void callCityList() {
        Subscription subscribe = mEntityImp.callCityList()
                .subscribe(new SimpleSubscriber<ProcincesResult>(mHomeContainer.getBottomContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        handlingException(e);
                    }

                    @Override
                    public void onNext(ProcincesResult procincesResult) {
                        super.onNext(procincesResult);
                        onSuccessNext(procincesResult);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }


    public void loadCitysBeanCache(){
        ProcincesResult.ProcincesBean.CitysBean citysBean = HotelDao.getCitysBean(mHomeContainer.getBottomContext());
        if (citysBean != null && !citysBean.getName().equals("")){
            mHomeContainer.setTextCityName(citysBean.getName());
            HomeContainer.setCitysBean(citysBean);
        } else {
            String cityCode = PreferencesUtils.getCityCode(mHomeContainer.getBottomContext());
            String cityName = PreferencesUtils.getCityName(mHomeContainer.getBottomContext());
            if (cityCode != null && cityName != null){
                mHomeContainer.setTextCityName(cityName);
                citysBean = new ProcincesResult.ProcincesBean.CitysBean();
                citysBean.setName(cityName);
                citysBean.setId(Integer.valueOf(cityCode));
                HomeContainer.setCitysBean(new ProcincesResult.ProcincesBean.CitysBean());
            }
        }

    }

    public void saveCitysBean(ProcincesResult.ProcincesBean.CitysBean citysBean){
        HotelDao.saveCitysBean(mHomeContainer.getBottomContext(), citysBean);
    }

    public void handlingException(Throwable e) {
        if (e instanceof APIException) {
            mHomeContainer.showViewToast(e.getMessage());
        } else {
            mHomeContainer.showViewToast(e.getMessage());
        }
    }

    public void onSuccessNext(ProcincesResult procincesResult){
        HotelDao.saveProcincesResult(mHomeContainer.getBottomContext(), procincesResult);
    }



    @Override
    public void destroy() {
        if (mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription.unsubscribe();
        }
    }
}