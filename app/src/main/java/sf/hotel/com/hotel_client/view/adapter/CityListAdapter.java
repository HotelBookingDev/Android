package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.CustomRadioButton;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class CityListAdapter extends RecyclerViewBaseAdapter<CityListAdapter.ViewHolder> {

    private List<ProvincesResult.ProvincesBean.CityBean> mList = new ArrayList<>();

    //内部做的一个当前选中项是那个
    //TODO 没做每次进来第一次加载
    public int isCheckedPos = -1;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setList(List<ProvincesResult.ProvincesBean.CityBean> list) {
        this.mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public ProvincesResult.ProvincesBean.CityBean getListItem(int pos) {
        return mList.get(pos);
    }

    public void setList(ProvincesResult provincesResult) {
        mList.clear();
        List<ProvincesResult.ProvincesBean> procinces = provincesResult.getProvinces();
        for (ProvincesResult.ProvincesBean procincesBean : procinces) {
            List<ProvincesResult.ProvincesBean.CityBean> citys = procincesBean.getCitys();
            for (ProvincesResult.ProvincesBean.CityBean cityBean : citys) {
                mList.add(cityBean);
            }
        }

        setCount(mList.size());
        notifyDataSetChanged();
    }

    public CityListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.button.setText(mList.get(position).getName());

        if (position != isCheckedPos) holder.button.setChecked(false);

        if (mOnItemClickListener != null) {
            holder.button.setOnClickListener(v -> {
                //每次点击都记录当前位置,刷新整个界面
                int pos = holder.getLayoutPosition();
                if (isCheckedPos != pos) {
                    isCheckedPos = pos;
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

    public int getIsCheckedPos() {
        return isCheckedPos;
    }
}
