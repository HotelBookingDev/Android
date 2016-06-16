package sf.hotel.com.hotel_client.view.presenter;

import sf.hotel.com.data.interfaceeneity.IDetailEntityImp;
import sf.hotel.com.hotel_client.view.interfaceview.IDetailView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/14.
 */
public class IDetailPresenter extends SuperPresenter{

    IDetailView mIDetailView;

    IDetailEntityImp mIDetailEntityImp;

    public IDetailPresenter(IDetailView mIDetailView) {
        this.mIDetailView = mIDetailView;
        mIDetailEntityImp = new IDetailEntityImp();
    }
}