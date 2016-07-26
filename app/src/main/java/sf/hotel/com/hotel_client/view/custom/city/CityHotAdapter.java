package sf.hotel.com.hotel_client.view.custom.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.CustomRadioButton;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/25.
 */
public class CityHotAdapter extends BaseAdapter {


    private List<CityBean> mData;
    private Context mContext;


    public CityBean selectCityBean;

    public void clearSelectCityBean() {
        selectCityBean = null;
        notifyDataSetChanged();
    }

    public interface OnTextClickListener {
        void onTextClick(View view, int pos);
    }

    private OnTextClickListener mOnTextClickListener;

    public void setOnTextClickListener(OnTextClickListener mOnTextClickListener) {
        this.mOnTextClickListener = mOnTextClickListener;
    }


    public CityBean getSelectCityBean() {
        return selectCityBean;
    }

    public void setSelectCityBean(CityBean selectCityBean) {
        this.selectCityBean = selectCityBean;
        notifyDataSetChanged();
    }

    public List<CityBean> getData() {
        return mData;
    }

    public void setData(List<CityBean> data) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public CityHotAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityHotAdapter.ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CityBean data = mData.get(position);

        holder.button.setText(mData.get(position).getName());

        holder.button.setChecked(false);

        if (selectCityBean != null) {
            if (data.getCode() != selectCityBean.getCode()) {
                holder.button.setChecked(false);
            } else {
                holder.button.setChecked(true);
            }
        }
        if (mOnTextClickListener != null) {
            final ViewHolder finalHolder = holder;
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectCityBean == null || data.getCode() != selectCityBean.getCode()) {
                        selectCityBean = data;
                        notifyDataSetChanged();
                    }
                    mOnTextClickListener.onTextClick(finalHolder.button, position);
                }
            });
        }

        return convertView;
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
        return position;
    }

    public class ViewHolder {
        public CustomRadioButton button;

        public ViewHolder(View itemView) {
            button = (CustomRadioButton) itemView.findViewById(R.id.item_city_btn);
        }
    }
}
