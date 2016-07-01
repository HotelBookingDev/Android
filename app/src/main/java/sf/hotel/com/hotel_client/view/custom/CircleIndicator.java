package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/25.
 */
public class CircleIndicator extends View {

    //接收从activity传过来的ViewPager,实现联动
    private RecyclerViewPager mViewPager;
    //当前小圆点的对象
    private CircleShape mSelectIndicator;
    //所有小原点对象集合
    private List<CircleShape> mIndicatorLists = new ArrayList<CircleShape>();
    //小圆点的圆半径
    private float mIndicatorRadius;
    //小圆点之间的间隔
    private float mIndicatorMargin;
    //小圆点的背景
    private int mIndicatorBackground;
    //选中小圆点的背景
    private int mIndicatorSelectedBackground;
    //viewpager当前的位置
    private int mCurrentPosition = 0;//默认为0
    //ViewPager当前位置的偏移量
    private float mCurrentPositionOffset = 0;

    //下面是一些自定义属性的默认值
    private final static int DEFAULT_RADIUS = 10;        //默认半径
    private final static int DEFAULT_MARGIN = 50;  //默认间距
    private final static int DEFAULT_BACKGROUND = Color.BLACK;    //默认颜色
    private final static int DEFAULT_SELECTED_BACKGROUND = Color.YELLOW;   //默认选中颜色

    public CircleIndicator(Context context) {
        super(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
        mIndicatorRadius = ta.getDimensionPixelSize(R.styleable.CircleIndicator_indicator_radio,
                DEFAULT_RADIUS);
        mIndicatorMargin = ta.getDimensionPixelSize(R.styleable.CircleIndicator_indicator_margin,
                DEFAULT_MARGIN);
        mIndicatorBackground = ta.getColor(R.styleable.CircleIndicator_indicator_background,
                DEFAULT_BACKGROUND);
        mIndicatorSelectedBackground = ta.getColor(
                R.styleable.CircleIndicator_indicator_selected_background,
                DEFAULT_SELECTED_BACKGROUND);
        //回收资源
        ta.recycle();
    }

    /**
     * 从activity把ViewPager传递进来，实现ViewPager和Indicator联动
     *
     * @param viewPager
     */
    public void setViewPager(final RecyclerViewPager viewPager) {
        mViewPager = viewPager;
        createIndicators();
        createSelectIndicator();
        setUpdateChangeListener();
        requestLayout();
        invalidate();
    }

    /**
     * 监听ViewPager的改变，实现小圆点与ViewPager联动
     */
    private void setUpdateChangeListener() {
        int count = mViewPager.getAdapter().getItemCount();
        if (count == 0)
            return;

        mViewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int i, int i1) {
                if (mViewPager.getCurrentPosition() >= 1073741823){
                    mCurrentPosition = (mViewPager.getCurrentPosition() - 1073741823) % count;
                }else if (mViewPager.getCurrentPosition() < 1073741823){
                    mCurrentPosition = count - (1073741823 - (mViewPager.getCurrentPosition() + 1)) % count - 1;
                }
                //强制从新布局
                requestLayout();
                //重新绘制
                invalidate();
            }
        });
    }

    /**
     * 创建选择的小原点，就是随着viewPager移动而移动的小圆点
     */
    private void createSelectIndicator() {
        OvalShape circle = new OvalShape();
        ShapeDrawable drawable = new ShapeDrawable(circle);
        mSelectIndicator = new CircleShape(drawable);
        Paint paint = drawable.getPaint();
        paint.setColor(mIndicatorSelectedBackground);
        paint.setAntiAlias(true);
        //在目标图片顶部绘制源图像,从命名上也可以看出来就是把源图像绘制在上方，把选中的小原点绘制在上面
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        mSelectIndicator.setPaint(paint);
    }

    /**
     * 创建与ViewPager个数相同的导航小圆点
     */
    private void createIndicators() {
        for (int i = 0; i < mViewPager.getAdapter().getItemCount(); i++) {
            //用圆形Shape创建小圆点对象
            OvalShape circle = new OvalShape();
            ShapeDrawable drawable = new ShapeDrawable(circle);
            CircleShape circleShape = new CircleShape(drawable);
            Paint paint = drawable.getPaint();
            paint.setColor(mIndicatorBackground);
            paint.setAntiAlias(true);
            circleShape.setPaint(paint);
            mIndicatorLists.add(circleShape);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        layoutIndicatorLists(getWidth(), getHeight());
        layoutSelectIndicator(mCurrentPosition, mCurrentPositionOffset);
    }

    /**
     * 放置小圆点
     *
     * @param containerWidth
     * @param containerHeight
     */
    private void layoutIndicatorLists(int containerWidth, int containerHeight) {
        if (mIndicatorLists == null) {
            return;
        }
        //容器的水平中间线
        float yCoordinate = containerHeight * 0.5f;
        float startPosition = startDrawPosition(containerWidth);
        for (int i = 0; i < mIndicatorLists.size(); i++) {
            CircleShape item = mIndicatorLists.get(i);
            item.resizeShape(2 * mIndicatorRadius, 2 * mIndicatorRadius);
            //每个小原点的左上角Y位置
            item.setY(yCoordinate - mIndicatorRadius);
            //每个小圆点X开始位置
            float x = startPosition + (mIndicatorMargin + mIndicatorRadius * 2) * i;
            item.setX(x);
        }
    }

    /**
     * 获取总体小原点的开始位置
     *
     * @param containerWidth
     * @return
     */
    private float startDrawPosition(int containerWidth) {
        float tabItemsLength = mIndicatorLists.size() * (mIndicatorMargin + 2 * mIndicatorRadius) -
                mIndicatorMargin;
        if (containerWidth < tabItemsLength) {
            return 0;
        }
        //水平居中显示
        return (containerWidth - tabItemsLength) / 2;
    }

    /**
     * 放置滚动的小原点
     *
     * @param position
     * @param positionOffset
     */
    private void layoutSelectIndicator(int position, float positionOffset) {
        if (mSelectIndicator == null) {
            return;
        }
        if (mIndicatorLists.size() == 0) {
            return;
        }

        if (position >= mIndicatorLists.size()){
            return;
        }

        CircleShape item = mIndicatorLists.get(position);
        mSelectIndicator.resizeShape(item.getWidth(), item.getHeight());
        //设置滚动的小圆点的X位置偏移量
        float x = item.getX() + (mIndicatorMargin + mIndicatorRadius * 2) * positionOffset;
        mSelectIndicator.setX(x);
        mSelectIndicator.setY(item.getY());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIndicatorLists.size() == 0 || mSelectIndicator == null) {
            return;
        }
        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.MATRIX_SAVE_FLAG |
                Canvas.CLIP_SAVE_FLAG |
                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        for (CircleShape item : mIndicatorLists) {
            drawItem(canvas, item);
        }
        drawItem(canvas, mSelectIndicator);
        canvas.restoreToCount(sc);
    }

    /**
     * 绘制小圆点
     *
     * @param canvas
     * @param indicator
     */
    private void drawItem(Canvas canvas, CircleShape indicator) {
        canvas.save();
        canvas.translate(indicator.getX(), indicator.getY());
        indicator.getShape().draw(canvas);
        canvas.restore();
    }
}
