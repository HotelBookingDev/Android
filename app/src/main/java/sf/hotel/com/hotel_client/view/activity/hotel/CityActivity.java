package sf.hotel.com.hotel_client.view.activity.hotel;

import android.content.Intent;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.CityMessage;
import sf.hotel.com.hotel_client.view.fragment.HomeContainer;
import sf.hotel.com.hotel_client.view.fragment.hotel.CityFragment;

/**
 * author MZ
 * email sanfenruxi1@163.com
 * date 16/6/23.
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

        mHotelTitleView.addLeftClick(v -> finish());
    }

    private void onRxEvent() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(CityMessage.class)
                .subscribe(cityMessage -> {
                    if (cityMessage != null) {
                        switch (cityMessage.what) {
                            case CityMessage.ACTIVITY_FINISH_AND_RESULT:

                                ProvincesResult.ProcincesBean.CityBean cityBean = (ProvincesResult.ProcincesBean.CityBean) cityMessage.obj;

                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("city", cityBean);
                                intent.putExtras(bundle);
                                setResult(HomeContainer.CITY_REQUEST_CODE, intent);
                                finish();
                                break;
                        }
                    }
                }, throwable -> {

                });
        mCompositeSubscription.add(subscribe);
    }

    private void init() {
        loadRootFragment(R.id.activity_city_frame, CityFragment.newInstance());
    }
}
