package sf.hotel.com.hotel_client.view.fragment.hotel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.DividerItemDecoration;
import sf.hotel.com.hotel_client.view.fragment.BaseFragment;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/25.
 */
public class HotelsRecommendFragment extends BaseFragment {


    @BindView(R.id.fragment_horizontal_hotel_recyclerview)
    RecyclerView mRecyclerView;

    public static HotelsRecommendFragment newInstance() {

        Bundle args = new Bundle();

        HotelsRecommendFragment fragment = new HotelsRecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater,   container,   savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_horizontal_hotel, container, false);
        ButterKnife.bind(this,view);


        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        //设置间隔线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getBottomContext(),
                        DividerItemDecoration.HORIZONTAL_LIST));
        LinearLayoutManager layout = new LinearLayoutManager(getBottomContext(), LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layout);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_INDICATOR_END){
                    //TODO 跳转推荐列表
                }
            }
        });
    }
}
