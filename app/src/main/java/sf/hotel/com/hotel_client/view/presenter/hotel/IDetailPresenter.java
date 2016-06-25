package sf.hotel.com.hotel_client.view.presenter.hotel;

import sf.hotel.com.data.interfaceeneity.IDetailEntityImp;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IDetailView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/14.
 */
public class IDetailPresenter extends SuperPresenter {

    private IDetailView mIDetailView;

    private  IDetailEntityImp mIDetailEntityImp;

    public IDetailPresenter(IDetailView mIDetailView) {
        this.mIDetailView = mIDetailView;
        mIDetailEntityImp = new IDetailEntityImp();
    }

    @Override
    public void destroy() {

    }
}
