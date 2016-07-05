package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import sf.hotel.com.data.utils.LogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/5.
 */
public class PriceView extends View{

    private String text = "1234";
    private int textSize = 50;
    private int textColor = Color.GRAY;
    private int lineColor = Color.GRAY;


    private Paint textPaint, linePaint;


    public PriceView(Context context) {
        this(context, null);
    }

    public PriceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);
        linePaint.setColor(lineColor);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int baseX = (int) (canvas.getWidth() / 2 - textPaint.measureText(text) / 2);
        int baseY = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));

        canvas.drawText(text, baseX, baseY, textPaint);

        canvas.drawLine(baseX, canvas.getHeight() / 2, (canvas.getWidth() + baseX) / 2, canvas.getHeight() / 2, linePaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = widthMeasureSpec;
        int height = heightMeasureSpec;


        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY){
            width = (int) textPaint.measureText(text) + getPaddingLeft() + getPaddingRight();
        }

        if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY){
            height = (int) (textPaint.descent() - textPaint.ascent()) + getPaddingTop()  + getPaddingBottom();
        }


        LogUtils.d("" + width +"height" + height);
        setMeasuredDimension(width, height);
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        textPaint.setTextSize(textSize);
        invalidate();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        textPaint.setColor(textColor);
        invalidate();
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        linePaint.setColor(lineColor);
        invalidate();
    }
}
