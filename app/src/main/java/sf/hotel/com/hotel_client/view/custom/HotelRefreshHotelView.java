package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import sf.hotel.com.hotel_client.R;

/**
 * Created by "林其望".
 * DATE: 2016:07:27:17:17
 * email:1105896230@qq.com
 */

public class HotelRefreshHotelView extends SuperSwipeRefreshLayout {

    // Header View
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;

    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    public HotelRefreshHotelView(Context context) {
        super(context);
        init();
    }

    public HotelRefreshHotelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        this.setHeaderViewBackgroundColor(0xff888888);
        this.setHeaderView(createHeaderView());// add headerView
        this.setFooterView(createFooterView());
        this.setTargetScrollWithLayout(true);
    }


    private View createHeaderView() {
        View headerView = LayoutInflater.from(getContext())
                .inflate(R.layout.layout_head, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.mipmap.ic_launcher);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }

    private refresh mRefresh;

    public void setmRefresh(refresh mRefresh) {
        this.mRefresh = mRefresh;
        this.setOnPullRefreshListener(new OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                textView.setText("正在刷新");
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                mRefresh.refresh();
            }

            @Override
            public void onPullDistance(int distance) {

            }

            @Override
            public void onPullEnable(boolean enable) {

            }
        });
        this.setOnPushLoadMoreListener(new OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                footerTextView.setText("正在加载...");
                footerImageView.setVisibility(View.GONE);
                footerProgressBar.setVisibility(View.VISIBLE);
                mRefresh.loadMore();
            }

            @Override
            public void onPushDistance(int distance) {

            }

            @Override
            public void onPushEnable(boolean enable) {

            }
        });
    }

    public interface refresh {
        void loadMore();

        void refresh();
    }

    public void finsh(){
        setRefreshing(false);
        setLoadMore(false);
    }

    private View createFooterView() {
        View footerView = LayoutInflater.from(getContext())
                .inflate(R.layout.layout_footer, null);
        footerProgressBar = (ProgressBar) footerView
                .findViewById(R.id.footer_pb_view);
        footerImageView = (ImageView) footerView
                .findViewById(R.id.footer_image_view);
        footerTextView = (TextView) footerView
                .findViewById(R.id.footer_text_view);
        footerProgressBar.setVisibility(View.GONE);
        footerImageView.setVisibility(View.VISIBLE);
        footerImageView.setImageResource(R.mipmap.ic_launcher);
        footerTextView.setText("上拉加载更多...");
        return footerView;
    }
}
