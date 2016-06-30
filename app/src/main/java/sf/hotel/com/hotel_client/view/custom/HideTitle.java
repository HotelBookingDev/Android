package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/30.
 */
public class HideTitle extends RelativeLayout {

    ImageView leftImg;
    TextView midText;
    ImageView rightImg1;
    ImageView rightImg2;
    LinearLayout rightLinearLayout;
    NoScrollView scrollView;

    int leftImgResId;

    int rightImgResId1, rightImgResId2;

    String midString = "";


    public void setScrollView(NoScrollView scrollView) {
        this.scrollView = scrollView;
        scrollView.setOnScrollListener(new NoScrollView.onScrollListener() {
            @Override
            public void onScroll(View view, int x, int y, int oldX, int oldY) {
                if(y < getHeight()){
                    float alpha = ((float) getHeight() - y) / getHeight();
                    setAlpha(1 - alpha);
                }
            }
        });
    }

    RelativeLayout.LayoutParams leftImgParams,rightParams, midTextParams;
    LinearLayout.LayoutParams rightImgParams1, rightImgParams2;

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

        ta.recycle();

        initView();
    }

    public void setViewAlpha(float alpha){
        setBackgroundColor(Color.argb((int)alpha, 0x88, 0x88, 0x88));
    }

    private void initView() {
        setClickable(false);
        setViewAlpha(0);
        setBackgroundColor(Color.argb(0x88, 0x88, 0x88, 0x88));

        addLeftView();
        addMidView();
        addRightView();
    }

    private void addRightView() {

        if (rightLinearLayout == null){
            rightLinearLayout = new LinearLayout(getContext());
            rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            rightParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                rightParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
            }
            rightLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

            rightImg1 = new ImageView(getContext());
            rightImgParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rightImg1.setBackgroundResource(rightImgResId1);
            rightLinearLayout.addView(rightImg1, rightImgParams1);

            rightImg2 = new ImageView(getContext());
            rightImgParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rightImg2.setBackgroundResource(rightImgResId2);
            rightLinearLayout.addView(rightImg2, rightImgParams2);

        }
        addView(rightLinearLayout, rightParams);
    }



    private void addMidView() {
        if (midText == null){
            midText = new TextView(getContext());
            midTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            midTextParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            midText.setText(midText.getText());
        }
        addView(midText, midTextParams);
    }

    private void addLeftView() {
        if (leftImg == null){
            leftImg = new ImageView(getContext());
            leftImgParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            leftImgParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
            }
            leftImg.setBackgroundResource(leftImgResId);
        }
        addView(leftImg, leftImgParams);
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
