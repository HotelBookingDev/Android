package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.CustomRadioButton;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class CityListAdapter extends RecyclerViewBaseAdapter<CityListAdapter.ViewHolder> {

    private List<CityBean> mList = new ArrayList<>();


    public CityBean selectCityBean;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setList(List<CityBean> list) {
        this.mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public CityBean getListItem(int pos) {
        return mList.get(pos);
    }

    public void setList(ProvincesResult provincesResult) {
        mList.clear();
        List<ProvincesBean> procinces = provincesResult.getProvinces();
        for (ProvincesBean procincesBean : procinces) {
            List<CityBean> citys = procincesBean.getCitys();
            for (CityBean cityBean : citys) {
                mList.add(cityBean);
            }
        }

        setCount(mList.size());
        notifyDataSetChanged();
    }

    public CityListAdapter(Context context) {
        super(context);
        selectCityBean = new CityBean();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.button.setText(mList.get(position).getName());

        if (mList.get(position).getCode() != selectCityBean.getCode())
            holder.button.setChecked(false);

        if (mOnItemClickListener != null) {
            holder.button.setOnClickListener(v -> {
                //每次点击都记录当前位置,刷新整个界面
                int pos = holder.getLayoutPosition();
                if (mList.get(position).getCode() != selectCityBean.getCode()) {
                    selectCityBean = mList.get(pos);
                    notifyDataSetChanged();
                }
                mOnItemClickListener.onItemClick(holder.itemView, pos);
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CustomRadioButton button;

        public ViewHolder(View itemView) {
            super(itemView);
            button = (CustomRadioButton) itemView.findViewById(R.id.item_city_btn);
        }
    }

    public CityBean getSelectCityBean(){
        return selectCityBean;
    }

    public void setSelectCityBean(CityBean selectCityBean) {
        this.selectCityBean = selectCityBean;
        notifyDataSetChanged();
    }
}
