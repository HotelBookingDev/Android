package sf.hotel.com.hotel_client.utils;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

import java.io.File;

import sf.hotel.com.hotel_client.utils.imageconfig.glidebuilds.GlideBuild;
import sf.hotel.com.hotel_client.utils.imageconfig.glidebuilds.GlideBuildFactory;

/**
 * Created by FMT on 2016/6/13:13:30
 * EMAILE 1105896230@qq.com.
 */
public class HotelImageLoad {
    //内部已经做了对context具体类型的处理
    private static void loadImageByPath(Context context, ImageView view, String url,
            GlideBuild build) {
        DrawableTypeRequest<String> load = Glide.with(context).load(url);
        DrawableRequestBuilder drawableRequestBuilder = initConfig(load, build);
        if (drawableRequestBuilder == null) {
            load.into(view);
        } else {
            drawableRequestBuilder.into(view);
        }
    }

    private static void loadImageByResource(Context context, ImageView view, @IdRes int id,
            GlideBuild build) {
        DrawableTypeRequest<Integer> load = Glide.with(context).load(id);
        DrawableRequestBuilder drawableRequestBuilder = initConfig(load, build);
        if (drawableRequestBuilder == null) {
            load.into(view);
        } else {
            drawableRequestBuilder.into(view);
        }
    }

    private static void loadImageByFile(Context context, ImageView imageView, File file,
            GlideBuild build) {
        DrawableTypeRequest<File> load = Glide.with(context).load(file);
        DrawableRequestBuilder drawableRequestBuilder = initConfig(load, build);
        if (drawableRequestBuilder == null) {
            load.into(imageView);
        } else {
            drawableRequestBuilder.into(imageView);
        }
    }

    private static void loadImageByUrl(Context context, ImageView imageView, Uri uri,
            GlideBuild build) {
        DrawableTypeRequest<Uri> load = Glide.with(context).load(uri);
        DrawableRequestBuilder drawableRequestBuilder = initConfig(load, build);
        if (drawableRequestBuilder == null) {
            load.into(imageView);
        } else {
            drawableRequestBuilder.into(imageView);
        }
    }

    //分别进入各自的加载方法中
    public static void loadImage(Context context, ImageView imageView, Object object) {
        loadImage(context, imageView, object,
                GlideBuildFactory.INSTACNE.getBuild(GlideBuildFactory.SIMPLEBUILD, context));
    }

    private static void loadImage(Context context, ImageView imageView, Object object,
            GlideBuild build) {
        if (object instanceof String) {
            loadImageByPath(context, imageView, (String) object, build);
        } else if (object instanceof File) {
            loadImageByFile(context, imageView, (File) object, build);
        } else if (object instanceof Uri) {
            loadImageByUrl(context, imageView, (Uri) object, build);
        } else if (object instanceof Integer) {
            loadImageByResource(context, imageView, (Integer) object, build);
        }
    }

    public static void loadImageCircle(Context context, ImageView imageView, Object obj) {
        loadImage(context, imageView, obj,
                GlideBuildFactory.INSTACNE.getBuild(GlideBuildFactory.CLICLEBUISD, context));
    }

    private static DrawableRequestBuilder initConfig(DrawableTypeRequest mRequest,
            GlideBuild build) {
        DrawableRequestBuilder mDrawableRequestBuilder = null;
        if (build.isCircle()) {
            mDrawableRequestBuilder = mRequest.transform(build.getmBitmapTransformation());
        }
        return mDrawableRequestBuilder;
    }
}
