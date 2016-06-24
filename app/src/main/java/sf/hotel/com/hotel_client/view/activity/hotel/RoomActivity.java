package sf.hotel.com.hotel_client.view.activity.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.activity.FragConstant;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.MessageFactory;
import sf.hotel.com.hotel_client.view.event.hotel.RoomMessage;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/24.
 */
public class RoomActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        init();
        onRxEvent();
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            HotelResult.HotelsBean hotelsBean = (HotelResult.HotelsBean) bundle.getSerializable("room");
            RxBus.getDefault().post(MessageFactory.createRoomMessage(RoomMessage.INTENT_ROOM, hotelsBean));
        }
    }


    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(RoomMessage.class)
                .subscribe(new Action1<RoomMessage>() {
                    @Override
                    public void call(RoomMessage roomMessage) {
                        if (roomMessage != null){
                            switch (roomMessage.what){
                                case RoomMessage.INTENT_ROOM:
                                    HotelResult.HotelsBean hotelsBean = (HotelResult.HotelsBean) roomMessage.obj;
                                    String introduce = hotelsBean.getIntroduce();
                                    LogUtils.d("-->111",introduce);
                                    break;
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtils.d("-->111", throwable.getMessage());
                    }
                });
        mCompositeSubscription.add(subscribe);

    }

    protected void init() {
        loadRootFragment(R.id.activity_hotel_frame, getFragmentByKey(FragConstant.ROOM));
    }

}
