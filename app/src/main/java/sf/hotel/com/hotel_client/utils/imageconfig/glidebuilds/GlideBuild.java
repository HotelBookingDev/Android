package sf.hotel.com.hotel_client.utils.imageconfig.glidebuilds;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by FMT on 2016/6/13:14:16
 * EMAILE 1105896230@qq.com.
 */
public class GlideBuild {
    private static final int WITHE = 480;
    private static final int HEIGHT = 800;

    public BitmapTransformation getmBitmapTransformation() {
        return mBitmapTransformation;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public int getmWidh() {
        return mWidh;
    }

    public int getmHeight() {
        return mHeight;
    }

    //默认
    protected boolean isCircle = false;
    protected int mWidh = WITHE;
    protected int mHeight = HEIGHT;

    protected BitmapTransformation mBitmapTransformation;

    public GlideBuild setmBitmapTransformation(BitmapTransformation mBitmapTransformation) {
        this.mBitmapTransformation = mBitmapTransformation;
        return this;
    }

    public GlideBuild setCircle(boolean circle) {
        this.isCircle = circle;
        return this;
    }

    public GlideBuild setmWidh(int mWidh) {
        this.mWidh = mWidh;
        return this;
    }

    public GlideBuild setmHeight(int mHeight) {
        this.mHeight = mHeight;
        return this;
    }
}
