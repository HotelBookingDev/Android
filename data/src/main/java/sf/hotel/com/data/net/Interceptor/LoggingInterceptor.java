package sf.hotel.com.data.net.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import sf.hotel.com.data.utils.LogUtils;

/**
 * Created by sanfen on 16/6/6.
 */
public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtils.d(request.url().toString());
        Response response = chain.proceed(request);
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        LogUtils.d("content", content);
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }
}
