package sf.hotel.com.hotel_client.view.presenter.hotel;

import android.text.TextUtils;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.SearchItem;
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

    @Override
    public void destroy() {
        if (mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void loadSearchItem() {
        SearchItem searchItem = mEntityImp.getSearchItem(iHomeContainerView.getBottomContext());
        if (searchItem == null){
            searchItem = new SearchItem();
            if (searchItem.cityBean == null){
                searchItem.cityBean = new CityBean();
            }
        }
        iHomeContainerView.setmSearchItem(searchItem);
    }
}
