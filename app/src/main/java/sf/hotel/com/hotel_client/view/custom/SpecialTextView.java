package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;


/**
 * Created by "林其望".
 * DATE: 2016:07:25:14:39
 * email:1105896230@qq.com
 */

public class SpecialTextView extends TextView {
    public SpecialTextView(Context context) {
        this(context, null);
    }

    public SpecialTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private String title;
    SpannableString spannableString;

    public SpecialTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        title = getText().toString();
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.login_title_text_size));
        this.setTextColor(getResources().getColor(R.color.white));
        initTitlteStyle();
    }

    //   设置Title的字体风格
    private void initTitlteStyle() {
        if (title == null) return;
        if (spannableString == null) {
            spannableString = new SpannableString(title);
        }
        spannableString.setSpan(new TypefaceSpan("sans-serif"), 0, title.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 0,
                title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(0.8f), 0, title.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的两倍
        this.setText(spannableString);
    }
}
