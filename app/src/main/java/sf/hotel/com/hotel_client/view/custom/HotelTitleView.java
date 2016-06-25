package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * Created by 林其望
 * data：2016/6/22
 * email: 1105896230@qq.com
 */
public class HotelTitleView extends RelativeLayout {
    private int mLeftBtnIcon;
    private int mRightBtnIcon;
    private String mLeftBtnText;
    private String mRightBtnText;
    private View mBtnLeft, mBtnRight;
    private TextView mTvTitle;
    private String mTitle;
    private LayoutParams mLeftBtnParams;
    private LayoutParams mRightBtnParams;

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
        } finally {
            a.recycle();
        }
        init();
        this.setBackgroundResource(R.color.view_title);
    }

    private void init() {
        mLeftBtnParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLeftBtnParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mLeftBtnParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mRightBtnParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mRightBtnParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRightBtnParams.addRule(RelativeLayout.CENTER_VERTICAL);
        if (mLeftBtnIcon != 0) {
            addLeftButton(mLeftBtnIcon);
        } else if (!TextUtils.isEmpty(mLeftBtnText)) {
            addLeftView(mLeftBtnText);
        }

        if (mRightBtnIcon != 0) {
            addRightButton(mRightBtnIcon);
        } else if (!TextUtils.isEmpty(mRightBtnText)) {
            addRightButton(mRightBtnText);
        }
        mTvTitle = new TextView(getContext());
        LayoutParams titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        if (!isInEditMode()) {
            mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.title_text_size));
            mTvTitle.setTextColor(getResources().getColor(R.color.black));
        }
        mTvTitle.setLayoutParams(titleParams);
        mTvTitle.setText(mTitle);
        addView(mTvTitle);
    }

    public void addLeftView(String text) {
        if (mBtnLeft == null) {
            mBtnLeft = new TextView(getContext());
            mBtnLeft.setPadding(20, 0, 0, 0);
            mBtnLeft.setLayoutParams(mLeftBtnParams);
            ((TextView) mBtnLeft).setText(text);
            ((TextView) mBtnLeft).setTextSize(17);
            ((TextView) mBtnLeft).setTextColor(getResources().getColorStateList(R.color.black));
            addView(mBtnLeft);
        } else {
            ((TextView) mBtnLeft).setText(text);
        }
        invalidate();
        requestLayout();
    }

    public void addLeftButton(int iconId) {
        if (mBtnLeft == null) {
            mBtnLeft = new ImageView(getContext());
            mBtnLeft.setPadding(20, 0, 50, 0);
            mBtnLeft.setLayoutParams(mLeftBtnParams);
            addView(mBtnLeft);
        }
        ((ImageView) mBtnLeft).setImageResource(iconId);

        invalidate();
        requestLayout();
    }

    public void addRightButton(CharSequence text) {
        if (mBtnRight == null) {
            mBtnRight = new Button(getContext());
            mBtnRight.setPadding(0, 0, 20, 0);
            mBtnRight.setLayoutParams(mRightBtnParams);
            mBtnRight.setBackgroundColor(getResources().getColor(R.color.white));
            ((Button) mBtnRight).setTextSize(17);
            ((Button) mBtnRight).setTextColor(getResources().getColorStateList(R.color.white));
            addView(mBtnRight);
        }
        ((Button) mBtnRight).setText(text);

        invalidate();
        requestLayout();
    }

    public void addRightButton(int iconId) {
        if (mBtnRight == null) {
            mBtnRight = new ImageView(getContext());
            mBtnRight.setPadding(50, 0, 20, 0);
            mBtnRight.setLayoutParams(mRightBtnParams);
            addView(mBtnRight);
        }
        ((ImageView) mBtnRight).setImageResource(iconId);

        invalidate();
        requestLayout();
    }

    public void addLeftonClick(OnClickListener mOnClickListener) {
        if (mOnClickListener != null && mBtnLeft != null) {
            mBtnLeft.setOnClickListener(mOnClickListener);
        }
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }
}
