package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import sf.hotel.com.data.entity.ProcincesResult;
import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class CityListAdapter extends RecyclerViewBaseAdapter<CityListAdapter.ViewHolder>{


    List<ProcincesResult.ProcincesBean.CitysBean> mList = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public void setList(List<ProcincesResult.ProcincesBean.CitysBean> list) {
        this.mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public ProcincesResult.ProcincesBean.CitysBean getListItem(int pos){
        return mList.get(pos);
    }

    public void setList(ProcincesResult procincesResult) {
        mList.clear();
        List<ProcincesResult.ProcincesBean> procinces = procincesResult.getProcinces();
        for (ProcincesResult.ProcincesBean procincesBean : procinces){
            List<ProcincesResult.ProcincesBean.CitysBean> citys = procincesBean.getCitys();
            for (ProcincesResult.ProcincesBean.CitysBean citysBean : citys){
                mList.add(citysBean);
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

        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

        }


    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.item_city_btn);
        }
    }
}
