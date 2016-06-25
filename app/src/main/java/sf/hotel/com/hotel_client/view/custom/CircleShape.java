package sf.hotel.com.hotel_client.view.custom;

import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/25.
 */
class CircleShape {
    //设置默认值
    private float x = 0;
    private float y = 0;
    private ShapeDrawable shape;
    private Paint paint;

    public CircleShape(ShapeDrawable shape) {
        this.shape = shape;
    }

    public void setPaint(Paint value) {
        paint = value;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setX(float value) {
        x = value;
    }

    public float getX() {
        return x;
    }

    public void setY(float value) {
        y = value;
    }

    public float getY() {
        return y;
    }

    public void setShape(ShapeDrawable value) {
        shape = value;
    }

    public ShapeDrawable getShape() {
        return shape;
    }

    public float getWidth() {
        return shape.getShape().getWidth();
    }

    public void setWidth(float width) {
        Shape s = shape.getShape();
        s.resize(width, s.getHeight());
    }

    public float getHeight() {
        return shape.getShape().getHeight();
    }

    public void setHeight(float height) {
        Shape s = shape.getShape();
        s.resize(s.getWidth(), height);
    }

    public void resizeShape(final float width, final float height) {
        shape.getShape().resize(width, height);
    }
}
