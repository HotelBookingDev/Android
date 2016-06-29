package sf.hotel.com.hotel_client.view.activity.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.alipay.PayCallBack;
import sf.hotel.com.hotel_client.alipay.PayHelper;
import sf.hotel.com.hotel_client.alipay.PayParam;
import sf.hotel.com.hotel_client.view.activity.BaseActivity;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/24.
 */
public class PayActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frame = new FrameLayout(this);
        Button button = new Button(this);
        frame.addView(button, FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        setContentView(frame);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayParam payParam = new PayParam();

                payParam.setSubject("111");
                payParam.setBody("222");
                payParam.setOrderInfo("123123123");
                payParam.setTotal_fee("100");

                PayHelper.getInstance().pay(PayActivity.this, payParam, new PayCallBack() {
                    @Override
                    public void success() {
                        LogUtils.d("success");
                    }

                    @Override
                    public void failed() {
                        LogUtils.d("failed");
                    }
                });
            }
        });
    }
}
