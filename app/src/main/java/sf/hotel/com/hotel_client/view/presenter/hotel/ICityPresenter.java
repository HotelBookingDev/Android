package sf.hotel.com.hotel_client.view.presenter.hotel;

import android.view.View;

import java.util.List;

import rx.Subscription;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.interfaceeneity.hotel.CityEntity;
import sf.hotel.com.data.interfaceeneity.hotel.ICityEntityImp;
import sf.hotel.com.data.net.callback.SimpleSubscriber;
import sf.hotel.com.hotel_client.view.interfaceview.hotel.ICityView;
import sf.hotel.com.hotel_client.view.presenter.SuperPresenter;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ICityPresenter extends SuperPresenter {
    private ICityView mICityView;

    private CityEntity mICityEntityImp;

    public ICityPresenter(ICityView mICityView) {
        this.mICityView = mICityView;
        mICityEntityImp = new ICityEntityImp();
    }


    public void onTextClick(View view, int pos) {

//
//        CityBean selectCityBean = mICityView.getCityListAllAdapter();
//        saveSelectCity(selectCityBean);

    }

    public void onHotTextClick(View view, int pos){
//        CityBean selectCityBean = mICityView.getCityListAllAdapter();
//        saveSelectCity(selectCityBean);
    }


    public void getProcincesResult() {
        List<CityBean> cityBeen = mICityEntityImp.getProcincesResult(mICityView.getBottomContext());
        if (cityBeen  != null){
            mICityView.getCityListAllAdapter().setAllCityBeen(cityBeen);
            mICityView.getCityListAllAdapter().setHotCityBeen(cityBeen);
        } else {
            callCityList();
        }
    }

    public void getCityBean() {
        CityBean cityBean = mICityEntityImp.getCityBean(mICityView.getBottomContext());
        if (cityBean != null){
           // mICityView.setCityListAdapterSelect(cityBean);
        }
    }

    public void callCityList() {
        Subscription subscribe = mICityEntityImp.callCityList()
                .subscribe(new SimpleSubscriber<List<CityBean>>(mICityView.getBottomContext()){
                    @Override
                    public void onNext(List<CityBean> cityBeen) {
                        super.onNext(cityBeen);
                        mICityView.getCityListAllAdapter().setAllCityBeen(cityBeen);
                        mICityView.getCityListAllAdapter().setHotCityBeen(cityBeen);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        handlingException(e);
                    }
                });
        addSubsrcicitpition(subscribe);
    }

    public void saveSelectCity(CityBean cityBean) {
//        if (cityBean != null)
//            mICityEntityImp.saveCitysBean(mICityView.getBottomContext(), cityBean);
    }
}
