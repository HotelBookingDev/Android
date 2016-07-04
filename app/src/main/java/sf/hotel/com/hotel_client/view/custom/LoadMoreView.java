package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;

import com.lhh.ptrrv.library.footer.loadmore.BaseLoadMoreView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/4.
 */
public class LoadMoreView extends BaseLoadMoreView {

    private Paint paint;
    private RectF oval;



    public LoadMoreView(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    protected void onDrawLoadMore(Canvas c, RecyclerView parent) {
        super.onDrawLoadMore(c, parent);
    }
}
