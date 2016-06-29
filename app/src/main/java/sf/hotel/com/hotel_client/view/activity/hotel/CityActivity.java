package sf.hotel.com.hotel_client.view.activity.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.activity.FragConstant;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.CityMessage;
import sf.hotel.com.hotel_client.view.fragment.HomeContainer;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/23.
 */
public class CityActivity extends BaseActivity {

    @BindView(R.id.activity_city_back)
    HotelTitleView mHotelTitleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);
        init();
        onRxEvent();

        mHotelTitleView.addLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(CityMessage.class)
                .subscribe(new Action1<CityMessage>() {
                    @Override
                    public void call(CityMessage cityMessage) {
                        if (cityMessage != null) {
                            switch (cityMessage.what) {
                                case CityMessage.ACTIVITY_FINISH_AND_RESULT:

                                    ProcincesResult.ProcincesBean.CitysBean citysBean = (ProcincesResult.ProcincesBean.CitysBean) cityMessage.obj;

                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("city", citysBean);
                                    intent.putExtras(bundle);
                                    setResult(HomeContainer.CITY_REQUEST_CODE, intent);
                                    finish();
                                    break;
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    private void init() {
        loadRootFragment(R.id.activity_city_frame, getFragmentByKey(FragConstant.CITY));
    }
}
