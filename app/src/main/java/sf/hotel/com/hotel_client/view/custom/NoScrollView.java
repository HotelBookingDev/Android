package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;

import sf.hotel.com.data.utils.LogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/28.
 */
public class NoScrollView extends ScrollView {

    private GestureDetector mGestureDetector;

    int currentY = 0;
    final int dy = 20;

    public NoScrollView(Context context) {
        this(context, null);
    }

    public NoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new  NoScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }


    public interface onScrollListener {
        void onScroll(View view,int x,  int y, int oldX, int oldY);

    }

    private NoScrollView.onScrollListener onScrollListener;

    public void setOnScrollListener(NoScrollView.onScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);

        if (onScrollListener != null) {
            if (Math.abs(y - currentY) > dy){
                onScrollListener.onScroll(this, x, y, oldx, oldy);
                currentY = y;
            }
        }

    }

    class NoScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceY) > Math.abs(distanceX);
        }
    }
}
