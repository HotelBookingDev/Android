package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/22.
 */
public class CustomBookingSearch extends LinearLayout {
    TextView mName;

    TextView mContent;


    PopupWindow mPopupWindow;

    String mTextNameStr, mTextContentStr;
    int mTextNameColor, mTextConentColor;
    int mTextNameBackGround, nTextContentBackGround;

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

        mName.setText(mTextNameStr);
        mContent.setText(mTextContentStr);

        mName.setTextColor(getResources().getColor(mTextNameColor));
        mContent.setTextColor(getResources().getColor(mTextConentColor));

        mName.setBackgroundResource(mTextNameBackGround);
        mContent.setBackgroundResource(nTextContentBackGround);


        View popView = LayoutInflater.from(getContext()).inflate(R.layout.custom_booking_popuwindow, null);
        mPopupWindow = new PopupWindow(popView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setTouchInterceptor(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });





    }

}
