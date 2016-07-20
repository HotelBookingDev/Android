package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import sf.hotel.com.data.entity.netresult.hotel.room.RoomBean;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.HotelImageLoad;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/14.
 */
public class RoomRecyclerAdapter extends BaseRecyclerAdapter<RoomBean> {
    public RoomRecyclerAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_bed_v2, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, RoomBean data) {
        if (viewHolder instanceof RoomRecyclerAdapter.ViewHolder){
            RoomRecyclerAdapter.ViewHolder holder = (RoomRecyclerAdapter.ViewHolder) viewHolder;

//            if (data.getHouse_imgs() != null && data.getHouse_imgs().size() > 0){
//                HotelImageLoad.loadImage(mContext, holder.mImageView, data.getHouse_imgs().get(0));
//            }else {
                HotelImageLoad.loadImage(mContext,
                        holder.mImageView,
                        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3256474343,2114829206&fm=23&gp=0.jpg");
//            }



        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        ListView mListView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_bed_v2_img);
            mListView = (ListView) itemView.findViewById(R.id.item_bed_v2_bed_list);
        }
    }
}
