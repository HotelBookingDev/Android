package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public class CustomBookingItem extends RelativeLayout {

    TextView mTextName;
    TextView mTextContent;

    String mTextNameStr, mTextContentStr;
    int mTextNameColor, mTextConentColor;
    int mTextNameBackGround, nTextContentBackGround;


    public CustomBookingItem(Context context) {
        this(context, null);
    }

    public CustomBookingItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomBookingItem);

        mTextNameStr = typedArray.getString(R.styleable.CustomBookingItem_booking_item_name);
        mTextContentStr = typedArray.getString(R.styleable.CustomBookingItem_booking_item_content);

        mTextNameColor = typedArray.getResourceId(R.styleable.CustomBookingItem_booking_item_name_color, R.color.hotels_text_color_green);
        mTextConentColor = typedArray.getResourceId(R.styleable.CustomBookingItem_booking_item_content_color, R.color.hotels_text_color_gray);

        mTextNameBackGround = typedArray.getResourceId(R.styleable.CustomBookingItem_booking_item_name_background, R.color.hide);
        nTextContentBackGround = typedArray.getResourceId(R.styleable.CustomBookingItem_booking_item_content_background, R.color.content_bg);

        typedArray.recycle();


        initView();
    }

    private void initView() {

        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_booking_item, this);


        mTextName = (TextView) inflate.findViewById(R.id.custom_booking_item_name);
        mTextContent = (TextView) inflate.findViewById(R.id.custom_booking_item_content);

        mTextName.setText(mTextNameStr);
        mTextContent.setText(mTextContentStr);

        mTextName.setTextColor(getResources().getColor(mTextNameColor));
        mTextContent.setTextColor(getResources().getColor(mTextConentColor));

        mTextName.setBackgroundResource(mTextNameBackGround);
        mTextContent.setBackgroundResource(nTextContentBackGround);

    }

    public void setTextContent(String s){
        mTextContent.setText(s);
    }

    public String getTextContent(){
        return mTextContent.getText().toString();
    }

}
