package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/13.
 */
public class BedAgentAdapter  extends BaseAdapter{


    List<String > list = new ArrayList<>();
    Context context;

    public BedAgentAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bed_agent, parent , false);
            vh = new ViewHolder();
            vh.mText = (TextView) convertView.findViewById(R.id.item_bed_agent_title);

            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.mText.setText("nihaoa");

        return convertView;
    }

    class ViewHolder{
        TextView mText;
    }
}
