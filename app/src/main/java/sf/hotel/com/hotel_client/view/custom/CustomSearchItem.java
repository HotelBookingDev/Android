package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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
 * @date 16/7/11.
 */
public class CustomSearchItem extends RelativeLayout {

    ImageView mLeftImg;

    TextView mLeftText, mRightText;


    int leftColor, rightColor;

    int imgRes;

    String leftTextStr, rightTextStr;

    public CustomSearchItem(Context context) {
        super(context, null);
    }

    public CustomSearchItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomSearchItem);

        imgRes = typedArray.getResourceId(R.styleable.CustomSearchItem_custom_item_img, R.mipmap.close_circle_48px);
        leftTextStr = typedArray.getString(R.styleable.CustomSearchItem_custom_item_text1);
        rightTextStr = typedArray.getString(R.styleable.CustomSearchItem_custom_item_text2);
        leftColor = typedArray.getResourceId(R.styleable.CustomSearchItem_custom_item_color1, R.color.hotels_text_color_gray);
        rightColor = typedArray.getResourceId(R.styleable.CustomSearchItem_custom_item_color2, R.color.hotels_text_color_gray);

        typedArray.recycle();

        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_search_item, this);

        mLeftImg = (ImageView) inflate.findViewById(R.id.custom_search_item_img);
        mLeftText = (TextView) inflate.findViewById(R.id.custom_search_item_text1);
        mRightText = (TextView) inflate.findViewById(R.id.custom_search_item_text2);

        mLeftImg.setImageResource(imgRes);
        mLeftText.setText(leftTextStr);
        mRightText.setText(rightTextStr);

        mLeftText.setTextColor(leftColor);
        mRightText.setTextColor(rightColor);
    }


    public String getLeftTextStr() {
        return leftTextStr;
    }

    public void setLeftTextStr(String leftTextStr) {
        this.leftTextStr = leftTextStr;
        mLeftText.setText(leftTextStr);
    }

    public String getRightTextStr() {
        return rightTextStr;
    }

    public void setRightTextStr(String rightTextStr) {
        this.rightTextStr = rightTextStr;
        mRightText.setText(rightTextStr);
    }
}
