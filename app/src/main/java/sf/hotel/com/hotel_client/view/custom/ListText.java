package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public class ListText extends LinearLayout {

    private TextView title;
    private TextView childText;


    private String[] mContentList = {};
    private String titleStr;


    private LinearLayout.LayoutParams titleLayoutParams, childTextParams;


    public ListText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ListText);
        titleStr = ta.getString(R.styleable.ListText_title_text);
        String content = ta.getString(R.styleable.ListText_content_text);
        if (content != null){
            mContentList = content.split("\\n");
        }

        ta.recycle();
        initView();
    }

    private void initView() {
        setOrientation(VERTICAL);
        setPadding(20,20, 20, 20);
        createTitle();
        createChildView();
        invalidate();
    }

    private void createChildView() {
        for (String s : mContentList){
            childText = new TextView(getContext());
            childTextParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            childText.setGravity(Gravity.LEFT);
            childText.setText("." + s);
            addView(childText, childTextParams);
        }


    }

    private void createTitle() {
        if (title == null){
            title = new TextView(getContext());
            titleLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            title.setGravity(Gravity.CENTER);
            title.setText(titleStr);
        }
        addView(title, titleLayoutParams);
    }

}
