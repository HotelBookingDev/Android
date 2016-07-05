package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.DensityUtils;
import sf.hotel.com.hotel_client.utils.transulcent.TransulcentUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/30.
 */
public class HideTitle extends LinearLayout {

    ImageView leftImg;
    TextView midText;
    ImageView rightImg1;
    ImageView rightImg2;
    LinearLayout rightLinearLayout;
    boolean isStatusView;

    View statusView;
    RelativeLayout belowLayout;


    int imgSize;

    RelativeLayout.LayoutParams leftImgParams, rightParams, midTextParams ;
    LinearLayout.LayoutParams rightImgParams1, rightImgParams2,statusViewParams, belowLayoutParams;

    int leftImgResId;
    int rightImgResId1, rightImgResId2;
    String midString = "";
    float titleSize;



    public void setColorWithHex(String s){
        int i = Color.parseColor(s);
    }


    public HideTitle(Context context) {
        this(context, null);
    }

    public HideTitle(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HideTitle);

        leftImgResId = ta.getResourceId(R.styleable.HideTitle_left_img, 0);
        rightImgResId1 = ta.getResourceId(R.styleable.HideTitle_right_img1, 0);
        rightImgResId2 = ta.getResourceId(R.styleable.HideTitle_right_img2, 0);
        midString = ta.getString(R.styleable.HideTitle_mid_text);
        isStatusView = ta.getBoolean(R.styleable.HideTitle_add_status_view, true);
        titleSize = ta.getDimensionPixelSize(R.styleable.HideTitle_title_size, 18);

        ta.recycle();

        imgSize = DensityUtils.dp2px(getContext(), 36);

        initView();
    }

    public void setViewAlpha(float alpha){
        int a = (int) (255 * alpha);

        this.getBackground().mutate().setAlpha(a);
    }

    private void initView() {
        setClickable(false);
        setOrientation(VERTICAL);
        setViewAlpha(0);

        if (isStatusView)
            addStatusView();

        addBelowView();
        addLeftView();
        addMidView();
        addRightView();
    }

    private void addStatusView() {
        if (statusView == null){
            statusView = new View(getContext());
            statusViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , TransulcentUtils.getStatusBarHeight(getContext()));
        }
        addView(statusView, statusViewParams);
    }

    private void addBelowView() {
        if (belowLayout == null){
            belowLayout = new RelativeLayout(getContext());
            int titleHeight = getContext().getResources().getDimensionPixelOffset(R.dimen.title_height);
            belowLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , titleHeight);
        }
        addView(belowLayout, belowLayoutParams);
    }

    private void addRightView() {

        if (rightLinearLayout == null){
            rightLinearLayout = new LinearLayout(getContext());
            rightParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            rightParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                rightParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
            }
            rightLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

            rightImg1 = new ImageView(getContext());
            rightImgParams1 = new LinearLayout.LayoutParams(imgSize, imgSize);
            rightImg1.setBackgroundResource(rightImgResId1);
            rightLinearLayout.addView(rightImg1, rightImgParams1);

            rightImg2 = new ImageView(getContext());
            rightImgParams2 = new LinearLayout.LayoutParams(imgSize, imgSize);
            rightImg2.setBackgroundResource(rightImgResId2);
            rightLinearLayout.addView(rightImg2, rightImgParams2);

        }
        belowLayout.addView(rightLinearLayout, rightParams);
    }



    private void addMidView() {
        if (midText == null){
            midText = new TextView(getContext());
            midTextParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            midTextParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            midText.setText(midString);
           // midText.setTextSize(getContext().getResources().getDimension(R.dimen.title_text_size));
        }
        belowLayout.addView(midText, midTextParams);
    }

    private void addLeftView() {
        if (leftImg == null){
            leftImg = new ImageView(getContext());
            leftImgParams = new RelativeLayout.LayoutParams(imgSize, imgSize);
            leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            leftImgParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
            }
            leftImg.setBackgroundResource(leftImgResId);
        }
        belowLayout.addView(leftImg, leftImgParams);
    }


    public void addLeftViewOnClickListener(OnClickListener onClickListener){
        if (!leftImg.isClickable())
            leftImg.setClickable(true);
        leftImg.setOnClickListener(onClickListener);
    }


    public void addRightView1OnClickListener(OnClickListener onClickListener){
        if (!rightImg1.isClickable())
            rightImg1.setClickable(true);
        rightImg1.setOnClickListener(onClickListener);
    }

    public void addRightView2OnClickListener(OnClickListener onClickListener){
        if (!rightImg2.isClickable())
            rightImg2.setClickable(true);
        rightImg2.setOnClickListener(onClickListener);
    }

    public void setMidText(String midString){
        midText.setText(midString);
    }
}
