package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/27.
 */
public class CustomCheckBox extends LinearLayout {

    View view;

    CheckBox checkBox;

    TextView bookingReserve;

    public CustomCheckBox(Context context) {
        this(context, null);
    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }


    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_booking_item, this);

        view = inflate.findViewById(R.id.booking_checkbox_item);
        checkBox = (CheckBox) inflate.findViewById(R.id.booking_checkbox);
        bookingReserve = (TextView) inflate.findViewById(R.id.booking_reserve);



    }
}
