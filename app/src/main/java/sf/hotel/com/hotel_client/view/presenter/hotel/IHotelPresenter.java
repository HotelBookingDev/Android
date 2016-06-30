package sf.hotel.com.hotel_client.view.presenter.hotel;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.entity.netresult.HotelResult;
import sf.hotel.com.data.interfaceeneity.HotelsEntityImp;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.hotel_client.view.event.RxBus;
import sf.hotel.com.hotel_client.view.event.hotel.HotelMessage;
import sf.hotel.com.hotel_client.view.event.MessageFactory;
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

    CompositeSubscription mCompositeSubscription;

    public IHotelPresenter(IHotelsView mIHotelsView) {
        this.mIHotelsView = mIHotelsView;
        mHotelsEntity = new HotelsEntityImp();
        mCompositeSubscription = new CompositeSubscription();
    }

    public void loadMoreHotel() {

    }


    public HotelResult getHotelCache(){
        //TODO 需要处理如果当城市id和当前酒店列表不一致的情况处理 可能就默认将酒店列表的城市信息显示 将正确的覆盖
        return mHotelsEntity.getHotelCache(mIHotelsView.getBottomContext());
    }


    public void callHotelsByCityId(String cityId, String page){
        Subscription subscribe = mHotelsEntity.callHotelsByCityId(cityId, page).subscribe(new SimpleSubscriber<HotelResult>(mIHotelsView.getBottomContext()) {
            @Override
            public void onNext(HotelResult hotelResult) {
                super.onNext(hotelResult);
                mHotelsEntity.saveHotelCache(mIHotelsView.getBottomContext(), hotelResult);
                mIHotelsView.setHotelAdapterList(hotelResult);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mIHotelsView.showViewToast(e.getMessage() + "加载失败");
                LogUtils.d(e.getMessage());
            }
        });

        mCompositeSubscription.add(subscribe);
    }


    @Override
    public void destroy() {
        if (mHotelsEntity != null){
            mHotelsEntity = null;
        }

        if (!mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription.unsubscribe();
        }
    }
}
