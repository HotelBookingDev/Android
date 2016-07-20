package sf.hotel.com.hotel_client.view.activity.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import rx.functions.Action1;
import sf.hotel.com.data.entity.netresult.pay.PayResult;
import sf.hotel.com.data.net.ApiWrapper;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.alipay.PayCallBack;
import sf.hotel.com.hotel_client.alipay.PayHelper;
import sf.hotel.com.hotel_client.alipay.Result;
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
                ApiWrapper.getInstance().callPay("1").subscribe(new Action1<PayResult>() {
                    @Override
                    public void call(PayResult payResult) {
                        String s = payResult.getUrl();
                        PayHelper.getInstance().pay(PayActivity.this, s, new PayCallBack() {
                            @Override
                            public void success(Result result) {
                                button.setText(s);
                            }

                            @Override
                            public void failed(Result result) {
                                LogUtils.d("shibaisss");
                            }
                        });
                    }
                });
            }
        });
    }
}
