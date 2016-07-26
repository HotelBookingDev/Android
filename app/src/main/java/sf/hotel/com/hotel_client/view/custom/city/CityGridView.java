package sf.hotel.com.hotel_client.view.custom.city;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/25.
 */
public class CityGridView extends GridView{
    public CityGridView(Context context) {
        this(context, null);
    }

    public CityGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
