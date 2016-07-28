package sf.hotel.com.hotel_client.view.custom.city;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/25.
 */
public class CityLayout extends RelativeLayout {

    RecyclerView mAllRecycler;
    LetterListView mLetterListView;
    CityListAllAdapter mAllAdapter;
    EditText mSearchEdit;

    boolean isScroll = false;

    private TextView overlay;
    private OverlayThread overlayThread; // 显示首字母对话框
    Handler mHandler = new Handler();

    public CityLayout(Context context) {
        this(context, null);
    }

    public CityLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_city_listview, this);
        mAllRecycler = (RecyclerView) inflate.findViewById(R.id.custom_city_all_list);
        mLetterListView = (LetterListView) inflate.findViewById(R.id.custom_city_letter);
        mSearchEdit = (EditText) inflate.findViewById(R.id.custom_city_search_item);

        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || "".equals(s.toString())) {
                    //搜索内容
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        initOverlay();
        overlayThread = new OverlayThread();


        mAllRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAllAdapter = new CityListAllAdapter(getContext());

        mAllRecycler.setAdapter(mAllAdapter);

        mLetterListView.setOnTouchingLetterChangedListener(new LetterListView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int alpha = mAllAdapter.getAlpha(s);
                if (alpha != -1) {
                    mAllRecycler.scrollToPosition(alpha);
                }
                overlay.setText(s);
                overlay.setVisibility(VISIBLE);
                mHandler.removeCallbacks(overlayThread);
                mHandler.postDelayed(overlayThread, 1000);
            }
        });

        mAllRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    isScroll = true;
                }

                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }


    // 设置overlay不可见
    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }
    }

    // 初始化汉语拼音首字母弹出提示框
    private void initOverlay() {
        //mReady = true;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item_city_total, null);

        overlay = (TextView) view.findViewById(R.id.item_city_total);
        overlay.setVisibility(View.INVISIBLE);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        addView(view, lp);
        overlay.setVisibility(GONE);
    }


    public CityListAllAdapter getAllAdapter() {
        return (CityListAllAdapter) mAllRecycler.getAdapter();
    }


    public void setOnCityItemClickListener(CityListAllAdapter.OnCityItemClickListener onCityItemClickListener) {
        mAllAdapter.setOnCityItemClickListener(onCityItemClickListener);
    }


    public void setHotOnItemClickListener(CityHotAdapter.OnTextClickListener clickListener) {
        mAllAdapter.setOnTextClickListener(clickListener);
    }


    public CityBean getHotCityBean() {
        return mAllAdapter.getHotCityBean();
    }


    public void clearSearchHotCityBean() {
        mAllAdapter.clearSearchHotCityBean();
    }

    public List<CityBean> getAllCityBeen() {
        return mAllAdapter.getAllCityBeen();
    }

    public void setAllCityBeen(List<CityBean> allCityBeen) {
        mAllAdapter.setAllCityBeen(allCityBeen);
    }

    public List<CityBean> getHotCityBeen() {
        return mAllAdapter.getHotCityBeen();
    }

    public void setHotCityBeen(List<CityBean> hotCityBeen) {
        mAllAdapter.setHotCityBeen(hotCityBeen);
    }


    public void setSearchCity(CityBean cityBean) {
        mAllAdapter.setCurrCityBean(cityBean);
    }
}
