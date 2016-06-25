package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public class CaptchaButton extends RelativeLayout {

    @BindView(R.id.customCaptchaBtn)
    Button mButton;

    ButtonCount mButtonCount;

    public CaptchaButton(Context context) {
        this(context, null);
    }

    public CaptchaButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.custom_captcha_button, this, true);
        ButterKnife.bind(this, view);
        mButton.setClickable(false);
    }

    public void startTimer() {
        if (mButtonCount == null) {
            mButtonCount = new ButtonCount(10 * 1000, 1000);
        }
        setClickable(false);
        mButtonCount.start();
    }

    /*定义一个倒计时的内部类*/
    class ButtonCount extends CountDownTimer {
        public ButtonCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            mButton.setText("获取");
            setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String str = millisUntilFinished / 1000 + "";
            mButton.setText(str);
        }
    }
}
