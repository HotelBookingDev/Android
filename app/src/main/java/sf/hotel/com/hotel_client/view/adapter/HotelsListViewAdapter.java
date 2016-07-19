package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.Hotel1Bean;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.utils.HotelImageLoad;
import sf.hotel.com.hotel_client.view.custom.CustomRatingBar;
import sf.hotel.com.hotel_client.view.custom.PriceText;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public class HotelsListViewAdapter extends BaseAdapter {

    private List<Hotel1Bean> mData;
    private Context mContext;

    public HotelsListViewAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
    }

    public void addDatas(List<Hotel1Bean> data) {
        mData.addAll(data);
    }

    public void setDatas(List<Hotel1Bean> data) {
        if (data != null) {
            mData.clear();
            mData.addAll(data);
        }
    }

    public Hotel1Bean getDataById(int pos) {
        return mData.get(pos);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_hotels, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Hotel1Bean data = mData.get(position);

        holder.mName.setText(data.getName());
        holder.mRatingBar.setRatingBarCount(3.5f);

        if (data.getHotel_imgs() != null && data.getHotel_imgs().size() > 0) {
            HotelImageLoad.loadImage(mContext, holder.mImageView, data.getHotel_imgs().get(0));
        } else {
            HotelImageLoad.loadImage(mContext, holder.mImageView,
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3256474343,2114829206&fm=23&gp=0.jpg");
        }

        return convertView;
    }

    class ViewHolder {
        TextView mName;
        CustomRatingBar mRatingBar;
        PriceText mPrice;
        ImageView mImageView;

        public ViewHolder(View itemView) {
            mName = (TextView) itemView.findViewById(R.id.item_hotels_name);
            mRatingBar = (CustomRatingBar) itemView.findViewById(R.id.item_hotels_ratingBar);
            mPrice = (PriceText) itemView.findViewById(R.id.item_hotels_price);
            mImageView = (ImageView) itemView.findViewById(R.id.item_hotels_img);
        }
    }
}
