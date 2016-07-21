package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.room.RoomPackagesBean;
import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/20.
 */
public class BedListAdapter extends BaseRecyclerAdapter<RoomPackagesBean> {


    public BedListAdapter(Context mContext) {
        super(mContext);
    }


    View.OnClickListener mSubmitOnClickListener;


    public void setSubmitOnClickListener(View.OnClickListener mSubmitOnClickListener) {
        this.mSubmitOnClickListener = mSubmitOnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_bed_v2, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, RoomPackagesBean data) {
        if (viewHolder instanceof ViewHolder){
            ViewHolder holder = (ViewHolder) viewHolder;

            if (mSubmitOnClickListener != null){
                holder.mSubmit.setOnClickListener(mSubmitOnClickListener);
            }
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Button mSubmit;

        public ViewHolder(View view) {
            super(view);
            mSubmit = (Button) view.findViewById(R.id.item_room_pager_submit);
        }
    }
}
