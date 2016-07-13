package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.HotelHousesBean;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.data.entity.netresult.hotel.HouseBean;
import sf.hotel.com.data.entity.netresult.hotel.HousePackagesBean;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.PriceLayout;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/29.
 */
public class DialogBedAdapter extends BaseAdapter {

    List<String> houseList;

    List<HousePackagesBean> housePackagesBeanList;
    Context context;

    HousePackagesBean selcetHouse;


    View.OnClickListener mOnItemClickListener;


    public void setOnItemClickListener(View.OnClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public DialogBedAdapter(Context context) {
        this.context = context;
        houseList = new ArrayList<>();
        housePackagesBeanList = new ArrayList<>();
    }

    AdapterView.OnItemClickListener mBedListOnItemClickListener;

    public void setBedListOnItemClickListener(AdapterView.OnItemClickListener mBedListOnItemClickListener) {
        this.mBedListOnItemClickListener = mBedListOnItemClickListener;
    }

    @Override
    public int getCount() {
        //return hotelHousesBeanList == null ? 0 : hotelHousesBeanList.size();
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bed, parent, false);
            holder = new ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.item_bed_title);
            holder.price = (PriceLayout) convertView.findViewById(R.id.item_bed_price);
            holder.item = (ViewGroup) convertView.findViewById(R.id.item_bed_item);
            holder.bedList = (ListView) convertView.findViewById(R.id.item_bed_listView);
            holder.btnSelect = (Button) convertView.findViewById(R.id.item_bed_selected);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mOnItemClickListener != null){
            holder.item.setOnClickListener(mOnItemClickListener);
        }

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        holder.bedList.setAdapter(new BedAgentAdapter(context));

        if (mBedListOnItemClickListener != null){
            holder.bedList.setOnItemClickListener(mBedListOnItemClickListener);
        }

        holder.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.bedList.getVisibility() == View.GONE){
                    holder.bedList.setVisibility(View.VISIBLE);
                }else {
                    holder.bedList.setVisibility(View.GONE);
                }
            }
        });


        return convertView;
    }

    class ViewHolder{
        TextView title;
        PriceLayout price;
        ViewGroup item;
        ListView bedList;
        Button btnSelect;
    }
}
