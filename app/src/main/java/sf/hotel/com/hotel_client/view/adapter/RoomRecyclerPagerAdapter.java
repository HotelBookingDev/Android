package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/17.
 */
public class RoomRecyclerPagerAdapter extends RecyclerViewBaseAdapter<RoomRecyclerPagerAdapter.ViewHolder> {


    public RoomRecyclerPagerAdapter(Context context) {
        super(context);
        setCount(5);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_room_pager, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mTitle.setText("hahha");
//        holder.mPrice.setText("$ 1111");
//
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;

        TextView mTitle;
        TextView mPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.item_hotels_img);
            mTitle = (TextView) itemView.findViewById(R.id.item_hotels_name);
            mPrice = (TextView) itemView.findViewById(R.id.item_hotels_price);
        }
    }
}
