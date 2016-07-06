package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/6.
 */
public class HotelSearchTitle extends RelativeLayout{

    TextView mTextTag, mContent, mTextDescribe;


    String mTagStr, mContentStr, mDesStr;

    public HotelSearchTitle(Context context) {
        this(context, null);
    }

    public HotelSearchTitle(Context context, AttributeSet attrs) {
        super(context, attrs);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HotelSearchTitle);

        mTagStr = ta.getString(R.styleable.HotelSearchTitle_tag_text);
        mContentStr = ta.getString(R.styleable.HotelSearchTitle_hotel_title_content_text);
        mDesStr = ta.getString(R.styleable.HotelSearchTitle_des_text);

        ta.recycle();

        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_hotek_search_title, this);


        mTextTag = (TextView) inflate.findViewById(R.id.search_title_tag);
        mContent = (TextView) inflate.findViewById(R.id.search_title_content);
        mTextDescribe = (TextView) inflate.findViewById(R.id.search_title_describe);


        mTextTag.setText(mTagStr);
        mContent.setText(mContentStr);
        mTextDescribe.setText(mDesStr);

    }
}
