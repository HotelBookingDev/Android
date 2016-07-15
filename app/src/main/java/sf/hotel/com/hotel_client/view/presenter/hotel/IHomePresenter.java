package sf.hotel.com.hotel_client.view.presenter.hotel;

import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.interfaceeneity.hotel.HomeEntity;
import sf.hotel.com.data.interfaceeneity.hotel.IHomeEntityImp;
import sf.hotel.com.data.net.Exception.APIException;
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

    public IHomePresenter(IHomeContainerView iHomeContainerView) {
        this.iHomeContainerView = iHomeContainerView;
        mEntityImp = new IHomeEntityImp();
    }

    public void saveCityBean(CityBean cityBean) {
        HotelDao.saveCitysBean(iHomeContainerView.getBottomContext(), cityBean);
    }

    public CityBean getCityBean() {
        return HotelDao.getCitysBean(iHomeContainerView.getBottomContext());
    }

    public void handlingException(Throwable e) {
        if (e instanceof APIException) {
            iHomeContainerView.showViewToast(e.getMessage());
        } else {
            iHomeContainerView.showViewToast(e.getMessage());
        }
    }

    public void loadSearchItem() {
        SearchItem searchItem = mEntityImp.getSearchItem(iHomeContainerView.getBottomContext());
        if (searchItem == null) {
            searchItem = new SearchItem();
            if (searchItem.cityBean == null) {
                searchItem.cityBean = new CityBean();
            }
        }
        iHomeContainerView.setmSearchItem(searchItem);
    }
}
