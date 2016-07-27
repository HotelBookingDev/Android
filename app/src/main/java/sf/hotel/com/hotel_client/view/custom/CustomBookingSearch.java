package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.DensityUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/22.
 */
public class CustomBookingSearch extends LinearLayout {
    TextView mName;

    TextView mContent;

    View mContentView;

    PopupWindow mPopupWindow;
    RecyclerView.Adapter mAdapter;

    String mTextNameStr, mTextContentStr;
    int mTextNameColor, mTextConentColor;
    int mTextNameBackGround, nTextContentBackGround;

    OnTouchListener mOnPopWindowTouchListener;

    public OnTouchListener getOnPopWindowTouchListener() {
        return mOnPopWindowTouchListener;
    }

    public void setOnPopWindowTouchListener(OnTouchListener mOnPopWindowTouchListener) {
        this.mOnPopWindowTouchListener = mOnPopWindowTouchListener;
        mPopupWindow.setTouchInterceptor(mOnPopWindowTouchListener);
    }

    RecyclerView recyclerView;

    public CustomBookingSearch(Context context) {
        this(context, null);
    }

    public CustomBookingSearch(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomBookingItem);
        mTextNameStr = typedArray.getString(R.styleable.CustomBookingItem_booking_item_name);
        mTextContentStr = typedArray.getString(R.styleable.CustomBookingItem_booking_item_content);
        mTextNameColor = typedArray.getResourceId(R.styleable.CustomBookingItem_booking_item_name_color, R.color.hotels_text_color_green);
        mTextConentColor = typedArray.getResourceId(R.styleable.CustomBookingItem_booking_item_content_color, R.color.hotels_text_color_gray);
        mTextNameBackGround = typedArray.getResourceId(R.styleable.CustomBookingItem_booking_item_name_background, R.color.hide);
        nTextContentBackGround = typedArray.getResourceId(R.styleable.CustomBookingItem_booking_item_content_background, R.color.content_bg);

        typedArray.recycle();
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_booking_searchview, this);

        mName = (TextView) inflate.findViewById(R.id.custom_booking_search_name);
        mContent = (TextView) inflate.findViewById(R.id.custom_booking_search_content);
        mContentView = inflate.findViewById(R.id.custom_booking_search_item);

        mName.setText(mTextNameStr);
        mContent.setText(mTextContentStr);
        mName.setTextColor(getResources().getColor(mTextNameColor));
        mContent.setTextColor(getResources().getColor(mTextConentColor));
        mName.setBackgroundResource(mTextNameBackGround);
        mContent.setBackgroundResource(nTextContentBackGround);

        mContentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow){
                    hidePopWindow();
                }else {
                    showPopWindow();
                }
            }
        });
    }

    boolean isShow = false;

    public void setAdapter(RecyclerView.Adapter adapter){
        mAdapter = adapter;
        if (recyclerView != null){
            recyclerView.setAdapter(mAdapter);
        }
    }

    public void showPopWindow(){
        if (mPopupWindow != null && mPopupWindow.isShowing()){
            return;
        } else if (mPopupWindow == null){
            View popView = LayoutInflater.from(getContext()).inflate(R.layout.custom_booking_popuwindow, null);
            recyclerView = (RecyclerView) popView.findViewById(R.id.item_pop_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            if (mAdapter != null){
                recyclerView.setAdapter(mAdapter);
            }
            mPopupWindow = new PopupWindow(popView, mContentView.getWidth(), DensityUtils.dp2px(getContext(), 100));
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(R.color.bg_gray)));
            mPopupWindow.setTouchInterceptor(mOnPopWindowTouchListener);
        }
        mPopupWindow.update();
        mPopupWindow.showAsDropDown(mContent);
        isShow = true;
    }

    public void hidePopWindow(){
        if (mPopupWindow != null){
            mPopupWindow.dismiss();
            isShow = false;
        }

    }


    public void setContentText(String s){
        mContent.setText(s);
    }

}
