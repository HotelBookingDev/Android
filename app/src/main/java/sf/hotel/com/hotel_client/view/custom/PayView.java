package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.hotel_client.R;

/**
 * Created by "林其望".
 * DATE: 2016:07:20:18:22
 * email:1105896230@qq.com
 */

public class PayView extends FrameLayout implements View.OnTouchListener {
    public static final int PAYALIAL = 1;
    public static final int PAYWECHAT = 2;
    private Context mContext;
    private View mRoootView;
    private FancyButton btn;
    private EditText moneyText;
    private RadioButton Rbalipay;
    private RadioButton RbWeChat;

    private PayClick click;

    public PayView(Context context, PayClick click) {
        super(context);
        mContext = context;
        this.click = click;
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mRoootView = layoutInflater.inflate(R.layout.pay_view, this, true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initViews();
    }

    private void initViews() {
        btn = (FancyButton) mRoootView.findViewById(R.id.fb_pay);
        moneyText = (EditText) mRoootView.findViewById(R.id.ed_money);
        Rbalipay = (RadioButton) mRoootView.findViewById(R.id.alipay_pay);
        RbWeChat = (RadioButton) mRoootView.findViewById(R.id.wechat_pay);
        RbWeChat.setOnTouchListener(this);
        Rbalipay.setOnTouchListener(this);
        btn.setOnClickListener(v -> {
            if (click != null) {
                int position = PAYALIAL;
                if (RbWeChat.isChecked()) {
                    position = PAYWECHAT;
                }
                if (moneyText.getText().toString().equals("")) return;
                click.Click(moneyText.getText().toString(), position);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v instanceof RadioButton) {
            RbWeChat.setChecked(false);
            Rbalipay.setChecked(false);
            ((RadioButton) v).setChecked(true);
        }
        return false;
    }

    public interface PayClick {
        void Click(String money, int postion);
    }

    public View getRootView(){
        return mRoootView;
    }
}
