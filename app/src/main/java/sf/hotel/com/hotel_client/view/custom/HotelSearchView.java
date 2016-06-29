package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arlib.floatingsearchview.FloatingSearchView;

/**
 * Created by 林其望
 * data：2016/6/29
 * email: 1105896230@qq.com
 */
public class HotelSearchView extends FloatingSearchView {
    public HotelSearchView(Context context) {
        this(context, null);
    }

    public HotelSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        changeEditInfo();
    }

    private void changeEditInfo() {
        EditText editText = getEditText();
        if (editText != null) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    private EditText getEditText() {
        EditText mEditText;
        mEditText = (EditText) findView(this);
        return mEditText;
    }

    //递归搜索EditText 因为原控件不提供接口
    public View findView(ViewGroup viewGroup) {
        View view;
        if (viewGroup == null || viewGroup.getChildCount() == 0) return null;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                View view1 = findView((ViewGroup) childAt);
                if (view1 != null) {
                    view = view1;
                    return view;
                }
            } else {
                if (childAt instanceof EditText) {
                    return childAt;
                }
            }
        }
        return null;
    }
}
