package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.HotelImageLoad;
import sf.hotel.com.hotel_client.view.custom.CustomRatingBar;
import sf.hotel.com.hotel_client.view.custom.PriceText;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class HomePullViewAdapter extends BaseRecyclerAdapter<HotelsBean> {

    public HomePullViewAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotels, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, HotelsBean data) {
        if (viewHolder instanceof ViewHolder){
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.mName.setText(data.getName());
            holder.mRatingBar.setRatingBarCount(3.5f);

            if (data.getHotel_imgs() != null && data.getHotel_imgs().size() > 0){
                HotelImageLoad.loadImage(mContext, holder.mImageView, data.getHotel_imgs().get(0));
            }else {
                HotelImageLoad.loadImage(mContext,
                        holder.mImageView,
                        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3256474343,2114829206&fm=23&gp=0.jpg");
            }
        }
    }

    class ViewHolder extends BaseRecyclerAdapter.Holder {
        TextView mName;
        CustomRatingBar mRatingBar;
        PriceText mPrice;
        ImageView mImageView;


        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.item_hotels_name);
            mRatingBar = (CustomRatingBar) itemView.findViewById(R.id.item_hotels_ratingBar);
            mPrice = (PriceText) itemView.findViewById(R.id.item_hotels_price);
            mImageView = (ImageView) itemView.findViewById(R.id.item_hotels_img);
        }
    }
}
