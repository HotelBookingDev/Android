package sf.hotel.com.hotel_client.view.presenter.hotel;

import android.content.Context;

import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.interfaceeneity.hotel.ITimerEntityImp;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ITimerView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/18.
 */
public class TimerPresenter extends SuperPresenter {
    ITimerView mITimerView;
    ITimerEntityImp mITimerEntityImp;


    public TimerPresenter(ITimerView mITimerView) {
        this.mITimerView = mITimerView;
        mITimerEntityImp = new ITimerEntityImp();
    }

    public void getSearchItem() {
        SearchItem searchItem = mITimerEntityImp.getSearchItem(mITimerView.getBottomContext());
        if (searchItem == null){
            searchItem = new SearchItem();
        }
        mITimerView.setSearchItem(searchItem);
    }

    public void saveSearchItem() {
        mITimerEntityImp.saveSearchItem(mITimerView.getBottomContext(),
                mITimerView.getSearchItem());
    }


}
