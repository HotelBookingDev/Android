package sf.hotel.com.hotel_client.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * Created by 林其望 on 2016/6/16.
 */
//用于用户Fragment下的item
public class PersionItemView extends FrameLayout{

    private ImageView mIconView;
    private TextView textView;
    private ImageView mIconRightView;

    private int defalutIcon=R.mipmap.ic_launcher;
    private String defalutString="ItemView";
    public PersionItemView(Context context) {
        this(context,null);
    }

    public PersionItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PersionItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PersionItemView, 0, 0);
        try {
            defalutIcon = a.getResourceId(R.styleable.PersionItemView_leftIcon, defalutIcon);
            defalutString=a.getString(R.styleable.PersionItemView_context);
        } finally {
            a.recycle();
        }
    }

    private void initViews(Context context){
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_person, this);
        ImageView imageView= (ImageView) inflate.findViewById(R.id.iv_item_persion);
        TextView  textView= (TextView) inflate.findViewById(R.id.tv_item_persion_content);
        textView.setText(defalutString);
        HotelImageLoad.loadImage(context,imageView,defalutIcon);
    }
}
