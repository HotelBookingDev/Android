package sf.hotel.com.hotel_client.view.presenter;

import sf.hotel.com.hotel_client.view.interfaceview.IRoomView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/16.
 */
public class IRoomPresenter extends SuperPresenter{
    IRoomView mIRoomView;

    public IRoomPresenter(IRoomView mIRoomView) {
        this.mIRoomView = mIRoomView;
    }
}
