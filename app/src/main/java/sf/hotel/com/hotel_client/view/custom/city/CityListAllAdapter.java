package sf.hotel.com.hotel_client.view.custom.city;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.utils.pinyin.PinyinUtils;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.CustomSearchItem;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/25.
 */
public class CityListAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int LOCATION = 0;
    public final int HOT_CITY = 1;
    public final int NORMAL = 2;

    public CityBean currCityBean;

    public Map<String, Integer> selectList;

    private List<CityBean> allCityBeen;

    private List<CityBean> hotCityBeen;

    private Context mContext;

    private CityHotAdapter adapter;

    private CityHotAdapter.OnTextClickListener mOnTextClickListener;

    public CityHotAdapter.OnTextClickListener getOnTextClickListener() {
        return mOnTextClickListener;
    }

    public void setOnTextClickListener(CityHotAdapter.OnTextClickListener mOnTextClickListener) {
        this.mOnTextClickListener = mOnTextClickListener;
        adapter.setOnTextClickListener(mOnTextClickListener);
        adapter.notifyDataSetChanged();
    }


    public CityBean getCurrCityBean() {
        return currCityBean;
    }

    public void setCurrCityBean(CityBean currCityBean) {
        this.currCityBean = currCityBean;
        notifyDataSetChanged();
    }

    private OnCityItemClickListener mOnCityItemClickListener;

    private  AdapterView.OnItemClickListener mHotOnItemClickListener;

    public AdapterView.OnItemClickListener getHotOnItemClickListener() {
        return mHotOnItemClickListener;
    }

    public void setHotOnItemClickListener(AdapterView.OnItemClickListener mHotOnItemClickListener) {
        this.mHotOnItemClickListener = mHotOnItemClickListener;
    }

    public interface OnCityItemClickListener{
        void onCityItemClick(View v, int pos);
    }

    public OnCityItemClickListener getOnCityItemClickListener() {
        return mOnCityItemClickListener;
    }

    public void setOnCityItemClickListener(OnCityItemClickListener mOnCityItemClickListener) {
        this.mOnCityItemClickListener = mOnCityItemClickListener;
    }


    public CityListAllAdapter(Context mContext) {
        this.mContext = mContext;
        allCityBeen = new ArrayList<>();
        hotCityBeen = new ArrayList<>();

        selectList = new HashMap<>();

        selectList.put("定位", 0);
        selectList.put("热门", 1);

        adapter = new CityHotAdapter(mContext, CityListAllAdapter.this);
    }

    public List<CityBean> getAllCityBeen() {
        return allCityBeen;
    }

    public void setAllCityBeen(List<CityBean> allCityBeen) {
        if (allCityBeen != null && allCityBeen.size() > 0){
            this.allCityBeen.clear();
            this.allCityBeen.addAll(allCityBeen);
            Collections.sort(allCityBeen);

            for (int i = 0; i < allCityBeen.size(); i++){
                if (i == 0){
                    String curr = PinyinUtils.getPingYinFirst(allCityBeen.get(i).getName());
                    selectList.put(curr, i + 2);
                }else {
                    String curr = PinyinUtils.getPingYinFirst(allCityBeen.get(i).getName());
                    String before = PinyinUtils.getPingYinFirst(allCityBeen.get(i-1).getName());
                    if (!curr.equals(before)){
                        selectList.put(curr, i + 2);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<CityBean> getHotCityBeen() {
        return hotCityBeen;
    }

    public void setHotCityBeen(List<CityBean> hotCityBeen) {
        if (hotCityBeen != null && hotCityBeen.size() > 0){
            this.hotCityBeen.clear();
            this.hotCityBeen.addAll(hotCityBeen);
            Collections.sort(allCityBeen);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allCityBeen.size() + 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder vh = null;

        switch (viewType){
            case LOCATION:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_city_location, parent, false);
                vh = new LocationVH(view);
                break;
            case HOT_CITY:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_city_list, parent, false);
                vh = new HotCityVH(view);
                break;
            case NORMAL:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_city_all_city, parent, false);
                vh = new NormalVH(view);
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case LOCATION:
                LocationVH locationVH = (LocationVH) holder;
                if (currCityBean != null)
                    locationVH.item.setRightTextStr(currCityBean.getName());
                break;
            case HOT_CITY:
                HotCityVH hotCityVH = (HotCityVH) holder;
                if (mHotOnItemClickListener != null){
                    hotCityVH.gridView.setOnItemClickListener(mHotOnItemClickListener);
                }
                adapter.setData(hotCityBeen);
                break;
            case NORMAL:
                NormalVH normalVH = (NormalVH) holder;
                normalVH.mAlpha.setVisibility(View.GONE);
                int realPos = position - 2;
                CityBean cityBean = allCityBeen.get(realPos);
                normalVH.mName.setText(cityBean.getName());

                String curr = PinyinUtils.getPingYinFirst(normalVH.mName.getText().toString());
                Integer integer = selectList.get(curr);
                if (integer == position){
                    normalVH.mAlpha.setText(curr);
                    normalVH.mAlpha.setVisibility(View.VISIBLE);
                }

                if (mOnCityItemClickListener != null){
                    normalVH.mName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clearSearchHotCityBean();
                            currCityBean = cityBean;
                            mOnCityItemClickListener.onCityItemClick(normalVH.mName, realPos);
                            notifyDataSetChanged();
                        }
                    });
                }
                break;
        }
    }

    /**
     *
     * @param str
     * @return
     */
    public int getAlpha(String str) {
        Integer integer = selectList.get(str);
        if (integer == null){
            return -1;
        }
        return integer;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 2 ? position : 2;
    }



    public CityBean getHotCityBean(){
        return adapter.getSelectCityBean();
    }


    public void clearSearchHotCityBean(){
        currCityBean = null;
        adapter.clearSelectCityBean();
    }

    class LocationVH extends RecyclerView.ViewHolder{
        CustomSearchItem item;
        public LocationVH(View itemView) {
            super(itemView);
            item = (CustomSearchItem) itemView.findViewById(R.id.item_city_location);
        }
    }

    class HotCityVH extends RecyclerView.ViewHolder{
        CityGridView gridView;
        public HotCityVH(View itemView) {
            super(itemView);
            gridView = (CityGridView) itemView.findViewById(R.id.item_city_grid);
            gridView.setAdapter(adapter);
        }
    }

    class NormalVH extends RecyclerView.ViewHolder{
        TextView mAlpha, mName;

        public NormalVH(View itemView) {
            super(itemView);
            mAlpha = (TextView) itemView.findViewById(R.id.item_city_alpha);
            mName = (TextView) itemView.findViewById(R.id.item_city_name);
        }
    }
}
