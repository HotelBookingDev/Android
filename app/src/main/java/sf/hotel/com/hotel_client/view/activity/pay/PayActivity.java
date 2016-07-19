package sf.hotel.com.hotel_client.view.activity.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;
import sf.hotel.com.data.net.ApiWrapper;
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
                ApiWrapper.getInstance().callPay("1").subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String s = responseBody.string();
                            PayHelper.getInstance().pay(PayActivity.this, s, new PayCallBack() {
                                @Override
                                public void success() {
                                    LogUtils.d("我成功了！！");
                                }

                                @Override
                                public void failed() {
                                    LogUtils.d("。。。！！");
                                }
                            });


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
}
