package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
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


    public DialogBedAdapter(Context context) {
        this.context = context;
        houseList = new ArrayList<>();
        housePackagesBeanList = new ArrayList<>();
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
            holder.check = (RadioButton) convertView.findViewById(R.id.item_bed_selected);
            holder.price = (PriceLayout) convertView.findViewById(R.id.item_bed_price);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

//        if (selcetHouse.getId() !=
//                housePackagesBeanList.get(position).getId()){
//            holder.check.setChecked(false);
//        }
//
//
//        holder.check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!holder.check.isChecked()){
//                    holder.check.setChecked(true);
//                    selcetHouse = housePackagesBeanList.get(position);
//                    notifyDataSetChanged();
//                }
//            }
//        });

        //holder.title.setText(housePackagesBeanList.get(position).getDetail());

        //holder.price.setNowPriceText(hotelHousesBeanList.get(position));

        return convertView;
    }

    class ViewHolder{
        TextView title;
        RadioButton check;
        PriceLayout price;
    }
}
