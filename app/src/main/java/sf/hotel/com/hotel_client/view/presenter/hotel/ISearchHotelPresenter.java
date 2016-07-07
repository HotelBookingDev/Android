package sf.hotel.com.hotel_client.view.presenter.hotel;

import sf.hotel.com.data.interfaceeneity.hotel.ISearchHotelEntityImp;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ISearchHotelView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public class ISearchHotelPresenter extends SuperPresenter {

    ISearchHotelEntityImp mISearchHotelEntityImp;

    ISearchHotelView mISearchHotelView;

    public ISearchHotelPresenter(ISearchHotelView mISearchHotelView) {
        this.mISearchHotelView = mISearchHotelView;
        mISearchHotelEntityImp = new ISearchHotelEntityImp();
    }

    @Override
    public void destroy() {

    }
}
