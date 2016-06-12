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
        return response;
    }
}
