package sf.hotel.com.data.net.Interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.net.AppUrl;
import sf.hotel.com.data.utils.CheckUtils;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * Created by 林其望
 * email: 1105896230@qq.com
 */
public class HeadInterceptor implements Interceptor {
    private static final String TOEKNKEY = "Authorization";
    private static final String JWT = "Token ";

    @Override
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
        final Request[] request = {chain.request()};
        isAddToken(request[0].url().toString(), request[0]).subscribe(reques -> {
            request[0] = reques;
        }, LogUtils::logThrowadle);
        okhttp3.Response response = chain.proceed(request[0]);
        saveToken(response);
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }

    private Request addToken(Request request) {
        String token = PreferencesUtils.getToken(EntityContext.getContext());
        if (!TextUtils.isEmpty(token)) {
            //添加token
            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader(TOEKNKEY, JWT + token)
                    .method(request.method(), request.body());
            request = requestBuilder.build();
        }
        return request;
    }

    private void saveToken(okhttp3.Response response) {
        String token = response.header("token");
        if (!TextUtils.isEmpty(token)) {
            //去除收尾空格
            token = token.trim();
            PreferencesUtils.saveToken(EntityContext.getContext(), token);
        }
    }

    private Observable<Request> isAddToken(String url, Request request) {
        return Observable.just(url)
                .filter(s -> {
                    return !CheckUtils.isTextViewEmpty(url);
                })
                .map(s -> {
                    return s.split(AppUrl.API_HOST);
                })
                .map(new Func1<String[], String>() {
                    @Override
                    public String call(String[] strings) {
                        return strings[1];
                    }
                })
                .filter(s -> !CheckUtils.isTextViewEmpty(s))
                .filter(this::isNeed)
                .map(s -> addToken(request));
    }

    private boolean isNeed(String url) {
        boolean isNeed = false;
        for (String s : AppUrl.NEEDTOKENURL) {
            if (s.equals(url)) {
                isNeed = true;
            }
        }
        if (!isNeed) {
            for (String s : AppUrl.SPEICALURL) {
                if (url.contains(s)) {
                    isNeed = true;
                }
            }
        }
        return isNeed;
    }
}
