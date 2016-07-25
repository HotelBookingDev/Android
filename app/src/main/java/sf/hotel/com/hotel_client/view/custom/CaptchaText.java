package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/7.
 */
public class CaptchaText extends Button {

    TextCount mTextCount;

    public CaptchaText(Context context) {
        this(context, null);
    }

    public CaptchaText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startTimer() {
        if (mTextCount == null) {
            mTextCount = new TextCount(10 * 1000, 1000);
        }
        setClickable(false);
        mTextCount.start();
    }

    /*定义一个倒计时的内部类*/
    class TextCount extends CountDownTimer {
        public TextCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            CaptchaText.this.setText("获取验证码");
            setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String str = millisUntilFinished / 1000 + "";
            CaptchaText.this.setText(str);
        }
    }
}
