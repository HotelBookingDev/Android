package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.DensityUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/6.
 */
public class PullScrollView extends FrameLayout {

    private FrameLayout mHeaderContainer;
    private NoScrollView mContentContainer;
    private FrameLayout mContentView;
    private int hideHeight;

    private boolean isEff;
    private int headerLayout;
    private int contentLayout;

    public interface HideHeardListener {
        void onHideView(float alpha);
    }

    HideHeardListener mHideHeardListener = new HideHeardListener() {
        @Override
        public void onHideView(float alpha) {
            mHeaderContainer.setAlpha(alpha);
        }
    };

    public void setHideHeardListener(HideHeardListener mHideHeardListener) {
        this.mHideHeardListener = mHideHeardListener;
    }

    private LayoutParams mHeaderParams, mContentParams, mContentViewParams;

    public PullScrollView(Context context) {
        this(context, null);
    }

    public PullScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PullScrollView);

        headerLayout = typedArray.getResourceId(R.styleable.PullScrollView_pull_header_layout, R.layout.header_room);
        contentLayout = typedArray.getResourceId(R.styleable.PullScrollView_pull_content_layout, R.layout.content_room);
        isEff = typedArray.getBoolean(R.styleable.PullScrollView_pull_is_eff, true);

        typedArray.recycle();
        hideHeight = DensityUtils.dp2px(context, 200);
        initView();
    }

    private void initView() {
        mHeaderContainer = new FrameLayout(getContext());
        mHeaderParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mHeaderContainer, mHeaderParams);

        mContentContainer = new NoScrollView(getContext());
        mContentParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

//        一个头 一个scoll 包了一个内容
        mContentView = new FrameLayout(getContext());
        mContentViewParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentContainer.addView(mContentView, mContentViewParams);

        addView(mContentContainer, mContentParams);

        mHeaderContainer.bringToFront();

        View header = LayoutInflater.from(getContext()).inflate(headerLayout, null);
        View content = LayoutInflater.from(getContext()).inflate(contentLayout, null);

        addHeaderView(header);
        addContentView(content);

        if (isEff){
            mContentContainer.setOnScrollListener(new NoScrollView.onScrollListener() {
                @Override
                public void onScroll(View view, int x, int y, int oldX, int oldY) {
                    int curr = y - 1500;

                    if (curr < hideHeight) {
                        float alpha = curr / (float) hideHeight;
                        if (alpha < 0) alpha = 0;
                        if (alpha > 1) alpha = 1;
                        mHideHeardListener.onHideView(alpha);
                    }
                }
            });
        }

    }

    public void addHeaderView(View view) {
        mHeaderContainer.removeAllViews();
        mHeaderContainer.addView(view);
    }

    public void addContentView(View view) {
        mContentView.removeAllViews();
        mContentView.addView(view);
    }
}
