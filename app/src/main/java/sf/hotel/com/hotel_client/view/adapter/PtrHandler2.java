package sf.hotel.com.hotel_client.view.adapter;

import android.view.View;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/15.
 */
public interface PtrHandler2 extends PtrHandler{

    /**
     * Check can do load more or not. For example the content is empty or the first child is in view.
     *
     * {@link PtrDefaultHandler#checkContentCanBePulledDown}
     */
    boolean checkCanDoLoadMore(final PtrFrameLayout frame, final View content, final View footer);

    /**
     * When load more begin
     *
     * @param frame
     */
    void onLoadMoreBegin(final PtrFrameLayout frame);
}