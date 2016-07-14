package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/14.
 */
public class CustomRatingBar extends RelativeLayout{


    RatingBar mRatingBar;

    TextView mTextView;

    String mTextStr;

    float mRatingCount;


    public CustomRatingBar(Context context) {
        this(context, null);
    }

    public CustomRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_ratingbar, this);

        mRatingBar = (RatingBar) inflate.findViewById(R.id.custom_ratingBar);

        mTextView = (TextView) inflate.findViewById(R.id.custom_ratingBar_text);




    }

    public void setTextViewText(String text){
        mTextView.setText(text);
    }

    public void setRatingBarCount(float f){
        mRatingBar.setRating(f);
    }

    public float getRatingBarCount(){
        return mRatingBar.getRating();
    }

}
