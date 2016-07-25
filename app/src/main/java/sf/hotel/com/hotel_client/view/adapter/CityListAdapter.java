package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
public class CityListAdapter extends BaseRecyclerAdapter<CityBean> {


    public CityListAdapter(Context mContext) {
        super(mContext);
    }


    public CityBean selectCityBean;

    public interface OnTextClickListener{
        void onTextClick(View view, int pos);
    }

    private OnTextClickListener mOnTextClickListener;

    public void setOnTextClickListener(OnTextClickListener mOnTextClickListener) {
        this.mOnTextClickListener = mOnTextClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, CityBean data) {
        if (viewHolder instanceof  CityListAdapter.ViewHolder){
            CityListAdapter.ViewHolder holder = (ViewHolder) viewHolder;
            holder.button.setText(data.getName());

            if (selectCityBean != null){
                if (data.getCode() != selectCityBean.getCode()){
                    holder.button.setChecked(false);
                }else {
                    holder.button.setChecked(true);
                }
            }
            if (mOnTextClickListener != null){
                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectCityBean == null || data.getCode() != selectCityBean.getCode()){
                            selectCityBean = data;
                            notifyDataSetChanged();
                        }
                        mOnTextClickListener.onTextClick(holder.button, RealPosition);
                    }
                });
            }
        }
    }

    public CityBean getSelectCityBean() {
        return selectCityBean;
    }

    public void setSelectCityBean(CityBean selectCityBean) {
        this.selectCityBean = selectCityBean;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CustomRadioButton button;

        public ViewHolder(View itemView) {
            super(itemView);
            button = (CustomRadioButton) itemView.findViewById(R.id.item_city_btn);
        }
    }
}
