package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.netresult.hotel.HotelHousesBean;
import sf.hotel.com.data.entity.netresult.hotel.HotelsBean;
import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/29.
 */
public class DialogBedAdapter extends BaseAdapter {

    List<String> houseList;

    List<HotelHousesBean> hotelHousesBeanList;
    Context context;

    public DialogBedAdapter(Context context) {
        this.context = context;
        houseList = new ArrayList<>();
        hotelHousesBeanList = new ArrayList<>();
    }

    public void setList(HotelsBean hotelsBean) {
        houseList = hotelsBean.getHouse_imgs();
        hotelHousesBeanList = hotelsBean.getHotel_houses();
    }

    @Override
    public int getCount() {
        return hotelHousesBeanList == null ? 0 : hotelHousesBeanList.size();
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
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        }
        return convertView;
    }
}
