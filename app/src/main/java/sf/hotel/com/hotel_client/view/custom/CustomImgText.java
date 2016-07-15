package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
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
 * @date 16/7/15.
 */
public class CustomImgText extends RelativeLayout {

    TextView mTextView;

    ImageView mImageView;

    public CustomImgText(Context context) {
        this(context, null);
    }

    public CustomImgText(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();

    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_img_text, this);

        mTextView = (TextView) inflate.findViewById(R.id.custom_img_text_text);
        mImageView = (ImageView) inflate.findViewById(R.id.custom_img_text_img);

    }


    public void setText(String text){
        mTextView.setText(text);
    }

    public String getText(){
        return mTextView.getText().toString();
    }

}
