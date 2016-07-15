package sf.hotel.com.hotel_client.view.presenter.person;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import sf.hotel.com.data.entity.LocalOrder;
import sf.hotel.com.data.interfaceeneity.person.ISearchOrder;
import sf.hotel.com.data.interfaceeneity.person.ISearchOrderImp;
import sf.hotel.com.hotel_client.view.interfaceview.person.ISearchView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * Created by 林其望
 * data：2016/6/28
 * email: 1105896230@qq.com
 */
public class SearchPresenter extends SuperPresenter {
    public SearchPresenter(ISearchView mISearchView) {
        this.mISearchView = mISearchView;
        mISearchOrder = new ISearchOrderImp();
    }

    ISearchView mISearchView;
    ISearchOrder mISearchOrder;

    public void getmLocalOrder() {
        Subscription subscribe = mISearchOrder.getOrders(mISearchView.getBottomContext())
                .subscribe(new Subscriber<List<LocalOrder>>() {
                    @Override
                    public void onCompleted() {
                        mISearchView.dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mISearchView.dismissProgress();
                    }

                    @Override
                    public void onNext(List<LocalOrder> localOrders) {
                        mISearchView.dismissProgress();
                        mISearchView.showSearch(localOrders);
                    }

                    @Override
                    public void onStart() {
                        mISearchView.showProgress();
                    }
                });
        addSubsrcicitpition(subscribe);
    }

    public void query(String query) {
        //TODO 做网络请求后保存数据库
        mISearchOrder.search(query);
        mISearchOrder.update(query, mISearchView.getBottomContext());
    }
}
