package sf.hotel.com.hotel_client.view.presenter.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.Date;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import sf.hotel.com.data.datasource.HotelDao;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.entity.SearchItem;
import sf.hotel.com.data.interfaceeneity.hotel.ISearchHotelEntityImp;
import sf.hotel.com.data.net.SelectDates;
import sf.hotel.com.data.utils.PreferencesUtils;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ISearchHotelView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public class ISearchHotelPresenter extends SuperPresenter {

    ISearchHotelEntityImp mISearchHotelEntityImp;

    ISearchHotelView mISearchHotelView;


    CompositeSubscription mCompositeSubscription;
    public ISearchHotelPresenter(ISearchHotelView mISearchHotelView) {
        this.mISearchHotelView = mISearchHotelView;
        mISearchHotelEntityImp = new ISearchHotelEntityImp();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void destroy() {
        if (mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }

    public void getCityBean() {
        CityBean cityBean = mISearchHotelEntityImp.getCityBean(mISearchHotelView.getBottomContext());

        if (cityBean != null && cityBean.getName()!= null){
            mISearchHotelView.getSearchItem().cityBean = cityBean;
        }
        mISearchHotelView.setTextCityName(mISearchHotelView.getSearchItem().cityBean.getName());
    }

    public void getSearchDate(Intent data){
        Bundle bundle = data.getExtras();
        SelectDates dates = (SelectDates) bundle.getSerializable("dates");
        if (dates != null && dates.dates.size() > 1){
            Date[] datasList = new Date[2];
            datasList[0] = dates.dates.get(0);
            datasList[1] = dates.dates.get(dates.dates.size() - 1);
            mISearchHotelView.setSearchTimer(datasList);
            mISearchHotelView.getSearchItem().inTime = datasList[0];
            mISearchHotelView.getSearchItem().outTime = datasList[1];
        }
    }


    public void callCityList() {
        Subscription subscribe = mISearchHotelEntityImp.callCityList()
                .subscribe(new Action1<ProvincesResult>() {
                    @Override
                    public void call(ProvincesResult provincesResult) {
                        if (provincesResult != null){
                            mISearchHotelEntityImp.saveProvincesResult(mISearchHotelView.getBottomContext(),
                                    provincesResult);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //handlingException(throwable);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    public SearchItem getSearchItem(Context context){
       return mISearchHotelEntityImp.getSearchItem(context);
    }

    public void saveSearchItem(){
        mISearchHotelEntityImp.saveSearchItem(mISearchHotelView.getBottomContext(), mISearchHotelView.getSearchItem());
    }

    public void loadSearchItem() {
        SearchItem searchItem = getSearchItem(mISearchHotelView.getBottomContext());
        if (searchItem == null){
            searchItem = new SearchItem();
        }
        mISearchHotelView.setSearchItem(searchItem);
        mISearchHotelView.setTextCityName(searchItem.cityBean.getName());
    }

}
