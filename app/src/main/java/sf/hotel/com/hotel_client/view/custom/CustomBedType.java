package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/14.
 */
public class CustomBedType extends RelativeLayout {
    public CustomBedType(Context context) {
        this(context, null);
    }

    public CustomBedType(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.custom_bed_type,this);
    }
}
