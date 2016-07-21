package sf.hotel.com.hotel_client.view.fragment.pay;

import android.view.Gravity;
import android.widget.TextView;

import sf.hotel.com.hotel_client.view.custom.PayBottomView;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;
import sf.hotel.com.hotel_client.view.interfaceview.person.IPayView;
import sf.hotel.com.hotel_client.view.presenter.person.PayPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/19.
 */
public class PayFragment extends BaseFragment implements IPayView {
    PayBottomView payBottomView;


    @Override
    public void showPayView(PayPresenter payPresenter) {
        payBottomView = new PayBottomView(getBottomContext(), payPresenter);
        payBottomView.showAtLocation(new TextView(getBottomContext()), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void dissMissPayView() {
         if (payBottomView!=null&&payBottomView.isShowing()){
             payBottomView.dismiss();
         }
    }
}
