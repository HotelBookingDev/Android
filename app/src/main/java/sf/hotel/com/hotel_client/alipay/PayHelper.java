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


    public void pay(Activity c, PayParam payParam, PayCallBack callBack){
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                LogUtils.e("100==", sign("100"));



                String orderInfo = getOrderInfo(payParam);

                LogUtils.e(orderInfo);


                /**
                 * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
                 */
                String sign = sign(orderInfo);
                try {
                    /**
                     * 仅需对sign 做URL编码
                     */
                    sign = URLEncoder.encode(sign, "UTF-8");


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                LogUtils.e(sign);

                String payInfo =  orderInfo + "&sign=\"" + sign + "\"&" + getSignType();


                LogUtils.d(payInfo);
                PayTask alipay = new PayTask(c);
                return alipay.pay(payInfo , true);
            }

            @Override
            protected void onPostExecute(String s) {

                LogUtils.e("s===" + s);
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

    public void pay(Activity c, String s, PayCallBack callBack){
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {


                String sign = sign("100");
                try {
                    /**
                     * 仅需对sign 做URL编码
                     */
                    sign = URLEncoder.encode(sign, "UTF-8");


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                LogUtils.e("100==", sign);


                LogUtils.d(s);
                PayTask alipay = new PayTask(c);
                return alipay.pay(s , true);


            }

            @Override
            protected void onPostExecute(String s) {

                LogUtils.d("s===" + s);
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


    private String getOrderInfo(PayParam payParam) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + Config.PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + Config.SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + payParam.getSubject() + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + payParam.getBody() + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + payParam.getTotal_fee() + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";
        return orderInfo;
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }


    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, Config.RSA_PRIVATE);
    }
}
