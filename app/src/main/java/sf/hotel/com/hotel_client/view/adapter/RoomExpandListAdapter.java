package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.room.RoomBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomStatusBean;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.PriceText;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/22.
 */
public class RoomExpandListAdapter implements ExpandableListAdapter {

    private Context mContext;

    private List<RoomBean> mData;

    private OnChildSubmitClickListener mChildSubmitClickListener;

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
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

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

        for (RoomStatusBean bean : roomstates){
            frontPrice += bean.getFront_price();
            needPoint += bean.getNeed_point();
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

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }




    class GroupHolder {
        ImageView mImageView, mMore;

        TextView mRoomName;
        public GroupHolder(View itemView) {
            mImageView = (ImageView) itemView.findViewById(R.id.item_bed_v2_img);
            mMore = (ImageView) itemView.findViewById(R.id.item_bed_v2_more);
            mRoomName = (TextView) itemView.findViewById(R.id.item_bed_v2_name);
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
