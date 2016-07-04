package sf.hotel.com.hotel_client.view.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

import sf.hotel.com.hotel_client.utils.HotelImageLoad;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public class LocalImageHodlerView implements Holder<String> {

    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        HotelImageLoad.loadImage(context, mImageView, data);
    }
}
