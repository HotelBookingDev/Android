package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import sf.hotel.com.data.entity.netresult.hotel.room.RoomBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomPackagesBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.HotelImageLoad;
import sf.hotel.com.hotel_client.view.custom.BedListView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/14.
 * @date 16/7/21.
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

    private View.OnClickListener mOnSubmitClickListener;

    private OnMoreStatusChangeListener mOnMoreStatusChangeListener;

    private interface OnMoreStatusChangeListener{
        int SHOW = 1;
        int HIDE = 0;
        void onStatusChange(int status, View view);
    }

    public void setOnSubmitClickListener(View.OnClickListener mOnSubmitClickListener) {
        this.mOnSubmitClickListener = mOnSubmitClickListener;
    }



    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, RoomBean data) {
        if (viewHolder instanceof RoomRecyclerAdapter.ViewHolder) {
            RoomRecyclerAdapter.ViewHolder holder = (RoomRecyclerAdapter.ViewHolder) viewHolder;

//            if (data.getHouse_imgs() != null && data.getHouse_imgs().size() > 0){
//                HotelImageLoad.loadImage(mContext, holder.mImageView, data.getHouse_imgs().get(0));
//            }else {
            HotelImageLoad.loadImage(mContext,
                    holder.mImageView,
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3256474343,2114829206&fm=23&gp=0.jpg");
//            }

            BedListAdapter bedAdapter = (BedListAdapter) holder.mListView.getAdapter();
            bedAdapter.setDatas(data.getRoomPackages());
            holder.mListView.requestLayout();


        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView, mMore;
        BedListView mListView;

        boolean isMore;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_bed_v2_img);
            mListView = (BedListView) itemView.findViewById(R.id.item_bed_v2_bed_list);
            mMore = (ImageView) itemView.findViewById(R.id.item_bed_v2_more);

            BedListAdapter bedListAdapter = new BedListAdapter(mContext);
            if (mOnSubmitClickListener != null){
                bedListAdapter.setSubmitOnClickListener(mOnSubmitClickListener);
            }
            mListView.setLayoutManager(new LinearLayoutManager(mContext));
            mListView.setAdapter(new BedListAdapter(mContext));

            mMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListView.setVisibility(View.INVISIBLE);
                    if (mOnMoreStatusChangeListener != null){

                    }
                }
            });
        }
    }
}