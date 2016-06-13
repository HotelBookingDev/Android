package sf.hotel.com.hotel_client.utils.imageconfig.glidebuilds;

import android.content.Context;

import sf.hotel.com.hotel_client.utils.imageconfig.transform.GlideCircleTransform;

/**
 * Created by FMT on 2016/6/13:14:32
 * EMAILE 1105896230@qq.com.
 */
public class CircleBuild extends GlideBuild {

    public CircleBuild(Context context) {
        this.isCircle = true;
        this.mBitmapTransformation = new GlideCircleTransform(context);
    }
}
