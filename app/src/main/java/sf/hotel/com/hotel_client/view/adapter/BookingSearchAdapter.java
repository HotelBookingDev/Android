package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/26.
 */
public class BookingSearchAdapter extends BaseRecyclerAdapter<String> {


    View.OnClickListener mOnClickListener;

    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public BookingSearchAdapter(Context mContext) {
        super(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_booking_search, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, String data) {
        if (viewHolder instanceof BookingSearchAdapter.ViewHolder){
            BookingSearchAdapter.ViewHolder holder = (ViewHolder) viewHolder;
            holder.textView.setText(data);
            if (mOnClickListener!= null){
                holder.view.setOnClickListener(mOnClickListener);
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.item_booking_search_item);
            textView = (TextView) itemView.findViewById(R.id.item_booking_search_text);
        }
    }
}
