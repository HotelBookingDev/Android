package sf.hotel.com.data.net;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sf.hotel.com.data.eneity.HttpResult;
import sf.hotel.com.data.eneity.UserEntity;

/**
 * Created by FMT on 2016/6/5:14:06
 * EMAILE 1105896230@qq.com.
 */
public class HttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private ApiService mApiService;
    private HttpMethods mInstance;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppUrl.SERVER_URL)
                .build();

        mApiService = retrofit.create(ApiService.class);
    }

    public HttpMethods getResource() {
        if (mInstance == null) {
            synchronized (this) {
                if (mInstance == null) {
                    mInstance = new HttpMethods();
                }
            }
        }
        return mInstance;
    }

    public rx.Observable<HttpResult<UserEntity>> getUserInfo(HashMap<String, String> map) {
        return mApiService.getUserInfo(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
