package sf.hotel.com.hotel_client.view.activity.person;

import android.os.Bundle;

import butterknife.ButterKnife;
import rx.Subscription;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.event.Message;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.person.OrderMessage;
import sf.hotel.com.hotel_client.view.fragment.person.OrderFragment;

public class OrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        loadRootFragment(R.id.order_content, OrderFragment.newInstance());
        ButterKnife.bind(this);
        initView();
        initRx();
    }

    private void initRx() {
        Subscription subscribe = RxBus.getDefault()
                .toObservable(OrderMessage.class)
                .subscribe(OrderMessage -> {
                    if (OrderMessage.what ==
                            Message.ISEXIT) {
                        finish();
                    } else if (OrderMessage.what == OrderMessage.SEARCHMESSAGE) {
                        start(SearchFragment.newInstance());
                    }
                }, throwable -> {
                });
        mCompositeSubscription.add(subscribe);
    }

    private void initView() {

    }
}
