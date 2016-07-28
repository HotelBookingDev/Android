package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.rippleview.RippleView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by 林其望
 * data：2016/6/22
 * email: 1105896230@qq.com
 */
public class HotelTitleView extends RippleView {
    private RelativeLayout mLayout;

    private RelativeLayout.LayoutParams mParams;

    private int mTitleBackground;

    private int mLeftBtnIcon;
    private int mRightBtnIcon;
    private String mLeftBtnText;
    private String mRightBtnText;
    private View mBtnLeft, mBtnRight;
    //存放mTvTitle 和mUnderline
    private LinearLayout mLinearLayout;
    private TextView mTvTitle;
    //中心文字旁边的下标
    private ImageView mUnderline;
    private boolean isShowUnderLine;
    private String mTitle;
    private LayoutParams mLeftBtnParams;
    private LayoutParams mRightBtnParams;
    SpannableString spannableString;

    public HotelTitleView(Context context) {
        this(context, null);
    }

    public HotelTitleView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public HotelTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.HotelTitleBar, 0, 0);
        try {
            mLeftBtnIcon = a.getResourceId(R.styleable.HotelTitleBar_leftBtnIcon, 0);
            mRightBtnIcon = a.getResourceId(R.styleable.HotelTitleBar_rightBtnIcon, 0);
            mLeftBtnText = a.getString(R.styleable.HotelTitleBar_leftBtnText);
            mRightBtnText = a.getString(R.styleable.HotelTitleBar_rightBtnText);
            mTitle = a.getString(R.styleable.HotelTitleBar_titleText);
            isShowUnderLine = a.getBoolean(R.styleable.HotelTitleBar_isShowUnderLine, false);
            mTitleBackground = a.getResourceId(R.styleable.HotelTitleBar_titleBackground, R.color.bg_black);
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {

        mLayout = new RelativeLayout(getContext());
        mParams = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        mLayout.setBackgroundResource(mTitleBackground);
        addView(mLayout, mParams);

        mLeftBtnParams = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        mLeftBtnParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mLeftBtnParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mRightBtnParams = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        mRightBtnParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRightBtnParams.addRule(RelativeLayout.CENTER_VERTICAL);
        if (mLeftBtnIcon != 0) {
            addLeftButton(mLeftBtnIcon);
        } else if (!TextUtils.isEmpty(mLeftBtnText)) {
            addLeftView(mLeftBtnText);
        }

        if (mRightBtnIcon != 0) {
            addRightView(mRightBtnIcon);
        } else if (!TextUtils.isEmpty(mRightBtnText)) {
            addRightView(mRightBtnText);
        }
        //初始化中间文字的组件
        initLinearlyGroup();
    }

    private void initLinearlyGroup() {
        mLinearLayout = new LinearLayout(getContext());
        LayoutParams linearParams = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mLinearLayout.setLayoutParams(linearParams);
        addContentView(mLinearLayout);
        mLayout.addView(mLinearLayout);
    }

    private void addContentView(ViewGroup mViewGroup) {
        //添加文字
        mTvTitle = new TextView(getContext());
        LayoutParams titleParams = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        if (!isInEditMode()) {
            mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.title_text_size));
            mTvTitle.setTextColor(getResources().getColor(R.color.white));
        }
        mTvTitle.setLayoutParams(titleParams);
        mTvTitle.setText(mTitle);
        mViewGroup.addView(mTvTitle);
        //添加下标
        if (isShowUnderLine) {
        }
        initTitlteStyle();
    }

    //   设置Title的字体风格
    private void initTitlteStyle() {
        if (mTitle == null) return;
        if (spannableString == null) {
            spannableString = new SpannableString(mTitle);
        }
        spannableString.setSpan(new TypefaceSpan("sans-serif"), 0, mTitle.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 0,
                mTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(0.8f), 0, mTitle.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的两倍
        mTvTitle.setText(spannableString);
    }


    private void addLeftView(String text) {
        if (mBtnLeft == null) {
            mBtnLeft = new TextView(getContext());
            mBtnLeft.setPadding(20, 0, 0, 0);
            mBtnLeft.setLayoutParams(mLeftBtnParams);
            ((TextView) mBtnLeft).setText(text);
            ((TextView) mBtnLeft).setTextSize(17);
            ((TextView) mBtnLeft).setTextColor(getResources().getColorStateList(R.color.white));
            ((TextView) mBtnLeft).setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.title_text_size));
            mLayout.addView(mBtnLeft);
        } else {
            ((TextView) mBtnLeft).setText(text);
        }
        invalidate();
        requestLayout();
    }

    private void addLeftButton(int iconId) {
        if (mBtnLeft == null) {
            mBtnLeft = new ImageView(getContext());
            mBtnLeft.setPadding(30, 0, 50, 0);
            mBtnLeft.setLayoutParams(mLeftBtnParams);
            mLayout.addView(mBtnLeft);
        }
        ((ImageView) mBtnLeft).setImageResource(iconId);

        invalidate();
        requestLayout();
    }

    private void addRightView(CharSequence text) {
        if (mBtnRight == null) {
            mBtnRight = new TextView(getContext());
            mBtnRight.setPadding(0, 0, 20, 0);
            mBtnRight.setLayoutParams(mRightBtnParams);
            ((TextView) mBtnRight).setTextSize(17);
            ((TextView) mBtnRight).setTextColor(getResources().getColorStateList(R.color.white));
            mLayout.addView(mBtnRight);
        }
        ((TextView) mBtnRight).setText(text);

        invalidate();
        requestLayout();
    }

    private void addRightView(int iconId) {
        if (mBtnRight == null) {
            mBtnRight = new ImageView(getContext());
            mBtnRight.setPadding(50, 0, 20, 0);
            mBtnRight.setLayoutParams(mRightBtnParams);
            mLayout.addView(mBtnRight);
        }
        ((ImageView) mBtnRight).setImageResource(iconId);

        invalidate();
        requestLayout();
    }

    public void addLeftClick(OnClickListener mOnClickListener) {
        if (mOnClickListener != null && mBtnLeft != null) {
            mBtnLeft.setOnClickListener(mOnClickListener);
        }
    }

    public void addRightClick(OnClickListener mOnClickListener) {
        if (mOnClickListener != null && mBtnRight != null) {
            mBtnRight.setOnClickListener(mOnClickListener);
        }
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }
}
