package sf.hotel.com.hotel_client.utils.imageconfig;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

import sf.hotel.com.data.config.HotelConstant;

/**
 * Created by FMT on 2016/6/13:13:24
 * EMAILE 1105896230@qq.com.
 */
//Glide全局配置
public class HotelGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        builder.setDiskCache(
                new DiskLruCacheFactory(HotelConstant.TEMP_IMG_DIR, HotelConstant.TEMP_IMG_SIZE));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
