package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/5.
 */
public class PriceLayout extends LinearLayout{

    ImageView leftImg;
    TextView nowPrice;
    PriceView oldPrice;

    LinearLayout.LayoutParams leftImgParams, nowPriceParams, oldPriceParams;


    int leftImgSrcId = R.mipmap.rmb_money_32px;
    int nowPriceTextColor = R.color.item_price_color;
    int oldProceTextColor = R.color.title_gray;


    String nowPriceText = "8888";
    String oldPriceText = "18888";

    public PriceLayout(Context context) {
        this(context, null);
    }

    public PriceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PriceLayout);



        initView();
    }

    private void initView() {

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);



        initLeftImg();
        initNowPrice();
        initOldPrice();
    }

    private void initOldPrice() {
        oldPrice = new PriceView(getContext());
        oldPriceParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        oldPrice.setTextColor(getResources().getColor(oldProceTextColor));
        oldPrice.setText(oldPriceText);

        addView(oldPrice, oldPriceParams);
    }

    private void initNowPrice() {
        nowPrice = new TextView(getContext());
        nowPriceParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        nowPrice.setTextColor(getResources().getColor(nowPriceTextColor));
        nowPrice.setText(nowPriceText);

        addView(nowPrice, nowPriceParams);
    }

    private void initLeftImg() {
        leftImg = new ImageView(getContext());
        leftImgParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        leftImg.setBackgroundResource(leftImgSrcId);

        addView(leftImg, leftImgParams);
    }


}
