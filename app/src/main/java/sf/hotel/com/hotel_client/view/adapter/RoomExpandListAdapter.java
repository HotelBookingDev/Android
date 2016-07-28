package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.BookingBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomStatusBean;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.PriceText;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/22.
 */
public class RoomExpandListAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    private List<RoomBean> mData;

    private OnChildSubmitClickListener mChildSubmitClickListener;

    private String price_type = BookingBean.PRICE_S;

    private int showGroupPos = -1;

    public void setShowGroupPos(int showGroupPos) {
        this.showGroupPos = showGroupPos;
    }

    public void setPrice_type(int adultCount){
        if (adultCount == 1){
            price_type = BookingBean.PRICE_S;
        }else {
            price_type = BookingBean.PRICE_D;
        }
    }

    public void setChildSubmitClickListener(OnChildSubmitClickListener mChildSubmitClickListener) {
        this.mChildSubmitClickListener = mChildSubmitClickListener;
    }

    public interface OnChildSubmitClickListener{
        void onChildSubmitClick(int groupPos, int  childPos);
    }


    public RoomExpandListAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    public void setDatas(List<RoomBean> rooms) {
        if (rooms != null){
            mData.clear();
            mData.addAll(rooms);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(groupPosition).getRoomPackages().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;


        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bed_v2, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (GroupHolder) convertView.getTag();
        }

        holder.mRoomName.setText(mData.get(groupPosition).getName());

        holder.mSplint.setVisibility(View.VISIBLE);


        if (showGroupPos != -1 && showGroupPos == groupPosition)
            holder.mSplint.setVisibility(View.GONE);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildHolder holder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bed_list_child, parent, false);

            holder = new ChildHolder(convertView);

            convertView.setTag(holder);
        }else {
            holder = (ChildHolder) convertView.getTag();
        }

        List<RoomStatusBean> roomstates = mData.get(groupPosition).getRoomPackages().get(childPosition).getRoomstates();

        float frontPrice = 0;
        float needPoint = 0;

        if (price_type.equals(BookingBean.PRICE_S) ){
            for (RoomStatusBean bean : roomstates){
                frontPrice += bean.getFront_price();
                needPoint += bean.getNeed_point();
            }
        }else {
            for (RoomStatusBean bean : roomstates){
                frontPrice += bean.getD_price();
                needPoint += bean.getNeed_point();
            }
        }

        holder.mPrice.setPoinsAndPrice(needPoint + "",frontPrice + "");


        holder.mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChildSubmitClickListener != null){
                    mChildSubmitClickListener.onChildSubmitClick(groupPosition, childPosition);
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        ImageView mImageView, mMore;
        View mSplint;

        TextView mRoomName;
        public GroupHolder(View itemView) {
            mImageView = (ImageView) itemView.findViewById(R.id.item_bed_v2_img);
            mMore = (ImageView) itemView.findViewById(R.id.item_bed_v2_more);
            mRoomName = (TextView) itemView.findViewById(R.id.item_bed_v2_name);
            mSplint = itemView.findViewById(R.id.item_bed_v2_splint);
        }
    }

    class ChildHolder{
        TextView mSubmit;
        PriceText mPrice;
        public ChildHolder(View view) {
            mSubmit = (TextView) view.findViewById(R.id.item_room_pager_submit);
            mPrice = (PriceText) view.findViewById(R.id.item_bed_price);
        }
    }
}
