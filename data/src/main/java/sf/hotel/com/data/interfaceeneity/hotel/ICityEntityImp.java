package sf.hotel.com.data.interfaceeneity.hotel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import sf.hotel.com.data.entity.CityBean;
import sf.hotel.com.data.entity.ProvincesBean;
import sf.hotel.com.data.entity.ProvincesResult;
import sf.hotel.com.data.net.ApiWrapper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/22.
 */
public class ICityEntityImp extends DataEntityImp implements CityEntity {

    @Override
    public Observable<List<CityBean>> callCityList() {

        return ApiWrapper.getInstance().callCityList()
                .flatMap(new Func1<ProvincesResult, Observable<List<CityBean>>>() {
                    @Override
                    public Observable<List<CityBean>> call(ProvincesResult result) {
                        return Observable.create(new Observable.OnSubscribe<List<CityBean>>() {
                            @Override
                            public void call(Subscriber<? super List<CityBean>> subscriber) {
                                List<ProvincesBean> provinces = result.getProvinces();
                                List<CityBean> cityBeen = new ArrayList<CityBean>();
                                for (ProvincesBean provincesBean : provinces){
                                    cityBeen.addAll(provincesBean.getCitys());
                                }
                                subscriber.onNext(cityBeen);

                                if (!subscriber.isUnsubscribed()){
                                    subscriber.onCompleted();
                                }
                            }
                        });
                    }
                });
    }
}
