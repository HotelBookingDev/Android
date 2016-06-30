package sf.hotel.com.hotel_client.view.activity.person;

import android.os.Bundle;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.fragment.person.MoneyFragment;

public class MoneyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        initView();
    }

    private void initView() {
        loadRootFragment(R.id.money_content, MoneyFragment.newInstance());
    }
}
