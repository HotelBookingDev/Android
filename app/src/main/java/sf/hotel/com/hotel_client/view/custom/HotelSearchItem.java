package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/6.
 */
public class HotelSearchItem extends RelativeLayout {

    private TextView mContent;
    private ImageView mClose;



    private String mText;
    private String mHintText;
    private int mCloseSrc;


    public HotelSearchItem(Context context) {
        this(context, null);
    }

    public HotelSearchItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HotelSearchItem);

        mText = ta.getString(R.styleable.HotelSearchItem_text);
        mHintText = ta.getString(R.styleable.HotelSearchItem_hint);

        mCloseSrc = ta.getResourceId(R.styleable.HotelSearchItem_close_src, R.mipmap.close_circle_48px);

        ta.recycle();

        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_hotel_search_item, this);

        mContent = (TextView) view.findViewById(R.id.hotel_search_item_content);
        mClose = (ImageView) view.findViewById(R.id.hotel_search_item_img_close);

        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(s)){
                    mClose.setVisibility(View.GONE);
                }else {
                    mClose.setVisibility(View.VISIBLE);
                }
            }
        });

        mContent.setHint(mHintText);
        mContent.setText(mText);


        mClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
            }
        });

        mClose.setVisibility(View.GONE);
    }

    public void setTextContent(String text){
        mContent.setText(text);
    }
}
