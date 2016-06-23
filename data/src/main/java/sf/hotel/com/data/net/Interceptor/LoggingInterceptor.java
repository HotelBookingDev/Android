package sf.hotel.com.data.net.Interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import sf.hotel.com.data.config.EntityContext;
import sf.hotel.com.data.utils.LogUtils;
import sf.hotel.com.data.utils.PreferencesUtils;

/**
 * Created by sanfen on 16/6/6.
 */
public class LoggingInterceptor implements Interceptor {
    private static final String TOEKNKEY = "token";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtils.d(request.url().toString());
        addToken(request);
        Response response = chain.proceed(request);
        saveToken(response);
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        LogUtils.d("content", content);
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }

    private Request addToken(Request request) {
        String token = PreferencesUtils.getToken(EntityContext.getContext());
        if (!TextUtils.isEmpty(token)) {
            //添加token
            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader(TOEKNKEY, token)
                    .method(request.method(), request.body());
            request = requestBuilder.build();
        }
        return request;
    }

    private void saveToken(Response response) {
        String token = response.header(TOEKNKEY);
        if (!TextUtils.isEmpty(token)) {
            PreferencesUtils.saveToken(EntityContext.getContext(), token);
        }
    }
}
