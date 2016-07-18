package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.hotel_client.R;

/**
 * Created by 林其望
 * data：2016/7/18
 * email: 1105896230@qq.com
 */
public class PayBottomView extends PopupWindow implements View.OnTouchListener {
    public static final int PAYALIAL = 1;
    public static final int PAYWECHAT = 2;
    private Context mContext;
    private View mPopUp;
    private FancyButton btn;
    private EditText moneyText;
    private RadioButton Rbalipay;
    private RadioButton RbWeChat;

    private PayClick click;

    public PayBottomView(Context context, PayClick click) {
        super(context);
        mContext = context;
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.click = click;
        init();
    }

    public PayBottomView(View contentView, int width, int height, boolean focusable,
            Context context) {
        super(contentView, width, height, focusable);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mPopUp = layoutInflater.inflate(R.layout.pay_view, null, false);
        setContentView(mPopUp);
        setTouchable(true);
        setFocusable(true);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setAnimationStyle(R.style.pay_menu_animation);
        initViews();
    }

    private void initViews() {
        btn = (FancyButton) mPopUp.findViewById(R.id.fb_pay);
        moneyText = (EditText) mPopUp.findViewById(R.id.ed_money);
        Rbalipay = (RadioButton) mPopUp.findViewById(R.id.alipay_pay);
        RbWeChat = (RadioButton) mPopUp.findViewById(R.id.wechat_pay);
        RbWeChat.setOnTouchListener(this);
        Rbalipay.setOnTouchListener(this);
        btn.setOnClickListener(v -> {
            if (click != null) {
                int position = PAYALIAL;
                if (RbWeChat.isChecked()) {
                    position = PAYWECHAT;
                }
                click.Click(Long.getLong(moneyText.getText().toString()), position);
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
        void Click(long money, int postion);
    }
}
