package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/21.
 */
public class CustomHotelText extends LinearLayout {

    private TextView mNameText, mContentText;

    private String mNameStr, mContentStr;

    public CustomHotelText(Context context) {
        this(context, null);
    }

    public CustomHotelText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomHotelText);
        mNameStr = typedArray.getString(R.styleable.CustomHotelText_hotel_text_name);
        mContentStr = typedArray.getString(R.styleable.CustomHotelText_hotel_text_content);

        typedArray.recycle();
        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_hotel_text, this);

        mNameText = (TextView) inflate.findViewById(R.id.custom_hotel_text_name);
        mContentText = (TextView) inflate.findViewById(R.id.custom_hotel_text_content);

        mNameText.setText(mNameStr);
        mContentText.setText(mContentStr);
    }


}
