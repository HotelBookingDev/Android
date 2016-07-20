package sf.hotel.com.hotel_client.alipay;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import sf.hotel.com.data.utils.LogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/24.
 */
public class PayHelper {

    public static PayHelper getInstance(){
        return new PayHelper();
    }

    public void pay(Activity c, String s, PayCallBack callBack){
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                LogUtils.d(s);
                PayTask alipay = new PayTask(c);
                return alipay.pay(s , true);
            }

            @Override
            protected void onPostExecute(String s) {
                LogUtils.d("s" + s);
                Result result = new Result(s);
                result.parseResult();
                if (result.isSuccess){
                    callBack.success(result);
                }else {
                    callBack.failed(result);
                }
            }
        }.execute();
    }
}
