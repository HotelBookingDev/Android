package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.yokeyword.fragmentation.SupportActivity;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.fragment.PersonFragment;

public class MainActivity extends SupportActivity implements View.OnClickListener {

    private PersonFragment mPersonFragment;

    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init(savedInstanceState);
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
    }

    private void init(Bundle savedInstanceState) {
        if (mPersonFragment == null) {
            mPersonFragment = new PersonFragment();
        }
        loadRootFragment(R.id.main_content, mPersonFragment);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn1:
                start(mPersonFragment);
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
        }
    }
}
