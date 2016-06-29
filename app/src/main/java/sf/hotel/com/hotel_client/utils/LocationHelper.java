package sf.hotel.com.hotel_client.utils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.HashMap;

import sf.hotel.com.hotel_client.HotelApplication;

public class LocationHelper {

    public static final String TAG = LocationHelper.class.getSimpleName();
    private LocationClient mLocationClient;
    private LocationListener mLocationListener;
    private LocationClientOption mOption;
    private final Object objLock = new Object();
    public static final String BLOCATIONSUCCESS = "bLocationSuccess";

    public static final String LOCAIONTYPE = "LocType";

    public static final String CITYCODE = "city_code";
    public static final String CITYNAME = "city_name";
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            if (mLocationListener != null) {
                HashMap locationMap = getHashMap(location);
                if (locationMap != null) {
                    if (locationMap.get(BLOCATIONSUCCESS) == Boolean.TRUE) {
                        mLocationListener.onGetLocation(locationMap);
                    } else {
                        mLocationListener.onError((Integer) locationMap.get(LOCAIONTYPE));
                    }
                }
            }
        }
    };

    private HashMap getHashMap(BDLocation location) {

        boolean bLocationSuccess = false;

        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            bLocationSuccess = true;
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            bLocationSuccess = true;
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果,没什么屌用
            bLocationSuccess = false;
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            bLocationSuccess = false;
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            bLocationSuccess = false;
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            bLocationSuccess = false;
        }

        HashMap hashMap = new HashMap();
        hashMap.put(BLOCATIONSUCCESS, bLocationSuccess);
        hashMap.put(LOCAIONTYPE, location.getLocType());
        hashMap.put(CITYCODE, location.getCityCode());
        hashMap.put(CITYNAME, location.getCity());
        return hashMap;
    }

    public LocationHelper(LocationListener mLocationListener) {
        mLocationClient = new LocationClient(HotelApplication.context);
        mLocationClient.setLocOption(getDefaultLocationClientOption());
        this.mLocationListener = mLocationListener;
    }

    public void start() {
        synchronized (objLock) {
            if (mLocationClient != null && !mLocationClient.isStarted()) {
                mLocationClient.registerLocationListener(mListener);
                mLocationClient.start();
            }
        }
    }

    public void stop() {
        synchronized (objLock) {
            if (mLocationClient != null && mLocationClient.isStarted()) {
                mLocationClient.stop();
                mLocationClient.unRegisterLocationListener(mListener);
            }
        }
    }

    public interface LocationListener {

        void onGetLocation(HashMap map);

        void onError(int type);
    }

    public LocationClientOption getDefaultLocationClientOption() {
        if (mOption == null) {
            mOption = new LocationClientOption();
            mOption.setLocationMode(
                    LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            mOption.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            mOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            mOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
            mOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
            mOption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            mOption.setIgnoreKillProcess(
                    true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            mOption.setIsNeedLocationDescribe(
                    true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            mOption.setIsNeedLocationPoiList(
                    true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        }
        return mOption;
    }
}
