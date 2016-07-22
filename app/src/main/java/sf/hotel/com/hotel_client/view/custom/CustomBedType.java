package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/14.
 */
public class CustomBedType extends RelativeLayout {

    private TextView textName, textTag1, textTag2, textTag3;
    private ImageView leftImg;


    private String name, tag1, tag2, tag3;

    public CustomBedType(Context context) {
        this(context, null);
    }

    public CustomBedType(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomBedType);

        name = typedArray.getString(R.styleable.CustomBedType_bed_type_name);
        tag1 = typedArray.getString(R.styleable.CustomBedType_bed_type_tag1);
        tag2 = typedArray.getString(R.styleable.CustomBedType_bed_type_tag2);
        tag3 = typedArray.getString(R.styleable.CustomBedType_bed_type_tag3);

        typedArray.recycle();


        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_bed_type, this);

        textName = (TextView) inflate.findViewById(R.id.bed_type_name);
        textTag1 = (TextView) inflate.findViewById(R.id.bed_type_tag1);
        textTag2 = (TextView) inflate.findViewById(R.id.bed_type_tag2);
        textTag3 = (TextView) inflate.findViewById(R.id.bed_type_tag3);
        leftImg = (ImageView) inflate.findViewById(R.id.bed_type_dot);

        setNameStr(name);
        setTag1Str(tag1);
        setTag2Str(tag2);
        setTag3Str(tag3);


    }

    public void setNameStr(String s){
        name = s;

        textName.setText(name);
        if (name == null || "".equals(name)){
            leftImg.setVisibility(GONE);
            textName.setVisibility(GONE);
        }else {
            leftImg.setVisibility(VISIBLE);
            textName.setVisibility(VISIBLE);
        }
    }

    public void setTag1Str(String s){
        tag1 = s;
        textTag1.setText(s);
        if (tag1 == null || "".equals(tag1)){
            textTag1.setVisibility(GONE);
        }else {
            textTag1.setVisibility(VISIBLE);
        }
    }

    public void setTag2Str(String s){
        tag2 = s;
        textTag2.setText(s);
        if (tag2 == null || "".equals(tag2)){
            textTag2.setVisibility(GONE);
        }else {
            textTag2.setVisibility(VISIBLE);
        }
    }

    public void setTag3Str(String s){
        tag3 = s;
        textTag3.setText(s);
        if (tag3 == null || "".equals(tag3)){
            textTag3.setVisibility(GONE);
        }else {
            textTag3.setVisibility(VISIBLE);
        }
    }
}
