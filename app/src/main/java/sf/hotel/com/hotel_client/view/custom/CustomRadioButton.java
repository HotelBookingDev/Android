package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/30.
 */
public class CustomRadioButton extends CompoundButton {


    private int text_color;
    private int select_text_color;

    public int DEFAULT_TEXT_COLOR = Color.BLACK;
    public int DEFAULT_SELECT_TEXT_COLOR = Color.GRAY;



    public CustomRadioButton(Context context) {
        this(context, null);
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton);

        text_color = ta.getColor(R.styleable.CustomRadioButton_text_color, DEFAULT_TEXT_COLOR);
        select_text_color = ta.getColor(R.styleable.CustomRadioButton_select_text_color, DEFAULT_SELECT_TEXT_COLOR);

        ta.recycle();
    }


    @Override
    public void toggle() {
        super.toggle();
        if (isChecked()){
            setTextColor(select_text_color);
        }else {
            setTextColor(text_color);
        }
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        if (checked){
            setTextColor(select_text_color);
        }else {
            setTextColor(text_color);
        }
    }
}
