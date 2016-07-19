package sf.hotel.com.hotel_client.alipay;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

                String payInfo =  s.substring(s.indexOf("?") + 1);
                PayTask alipay = new PayTask(c);
                return alipay.pay(payInfo , true);
            }

            @Override
            protected void onPostExecute(String s) {
                Result result = new Result(s);
                result.parseResult();

                String resultStatus = result.resultStatus;
                if (result.isSuccess){
                    callBack.success();
                }else {
                    callBack.failed();
                }

            }
        }.execute();
    }
}
