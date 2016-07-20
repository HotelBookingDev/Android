package sf.hotel.com.hotel_client.view.presenter.hotel;

import rx.Subscription;
import rx.functions.Action1;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.interfaceeneity.hotel.HotelsEntityImp;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.IHotelsView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/13.
 */
public class IHotelPresenter extends SuperPresenter {

    IHotelsView mIHotelsView;

    HotelsEntityImp mHotelsEntity;

    public IHotelPresenter(IHotelsView mIHotelsView) {
        this.mIHotelsView = mIHotelsView;
        mHotelsEntity = new HotelsEntityImp();
    }

    public void getHotelCache() {
        HotelResult hotelCache = mHotelsEntity.getHotelCache(mIHotelsView.getBottomContext());
        if (hotelCache != null)
        mIHotelsView.addHotelAdapterList(hotelCache);
    }

    public void loadHotelsByCityId(int page) {

        SearchItem item = mIHotelsView.getSearchItem();

        String ex = "types";

        Subscription subscribe = mHotelsEntity.callHotelsByCityId(item, String.valueOf(page), ex)
                .subscribe(new Action1<HotelResult>() {
                    @Override
                    public void call(HotelResult hotelResult) {
                        mHotelsEntity.saveHotelCache(mIHotelsView.getBottomContext(), hotelResult);
                        mIHotelsView.addHotelAdapterList(hotelResult);
                        mIHotelsView.loadMoreFinish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mIHotelsView.showViewToast(throwable.getMessage() + "加载失败");
                        LogUtils.d(throwable.getMessage());
                        mIHotelsView.loadMoreFinish();
                    }
                });

        addSubsrcicitpition(subscribe);
    }

    public void callHotelsByCityId(String page) {

        SearchItem item = mIHotelsView.getSearchItem();

        String ex = "";

        Subscription subscribe = mHotelsEntity.callHotelsByCityId(item, page, ex)
                .subscribe(new Action1<HotelResult>() {
                    @Override
                    public void call(HotelResult hotelResult) {
                        mHotelsEntity.saveHotelCache(mIHotelsView.getBottomContext(), hotelResult);
                        mIHotelsView.setHotelAdapterList(hotelResult);
                        mIHotelsView.refreshComplete();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mIHotelsView.showViewToast(throwable.getMessage() + "加载失败");
                        LogUtils.d(throwable.getMessage());
                        mIHotelsView.refreshComplete();
                    }
                });

        addSubsrcicitpition(subscribe);
    }

    public void loadSearchItem() {
        SearchItem searchItem = mHotelsEntity.getSearchItem(mIHotelsView.getBottomContext());
        if (searchItem == null) {
            searchItem = new SearchItem();
            if (searchItem.cityBean == null) {
                searchItem.cityBean = new CityBean();
            }
        }
        mIHotelsView.setSearchItem(searchItem);
    }

    @Override
    public void destroy() {
        super.destroy();
        if (mHotelsEntity != null) {
            mHotelsEntity = null;
        }
    }
}
