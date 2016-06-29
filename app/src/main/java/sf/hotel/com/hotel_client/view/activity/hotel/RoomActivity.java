package sf.hotel.com.hotel_client.view.activity.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.transulcent.TransulcentUtils;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;
import sf.hotel.com.hotel_client.view.fragment.hotel.RoomFragment;

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
        initIntent();
        TransulcentUtils.setColorWindow(this, R.color.colorPrimary);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                init(bundle);
            }
        }
    }


    protected void init(Bundle bundle) {
        loadRootFragment(R.id.activity_hotel_frame, RoomFragment.newInstance(bundle));
    }

}
