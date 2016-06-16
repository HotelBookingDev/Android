package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * Created by 林其望 on 2016/6/16.
 */
public class HotelMenuButton extends RelativeLayout{
    public HotelMenuButton(Context context) {
        this(context,null);
    }

    public HotelMenuButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public HotelMenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
