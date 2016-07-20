package sf.hotel.com.hotel_client.alipay;

import org.json.JSONObject;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import sf.hotel.com.data.utils.LogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/6/24.
 */
public class Result  {

    public static final Map<String, String> mResultStatus;

    private String mPayResult;

    public String resultStatus = null;
    public String memo = null;
    public String result = null;

    public boolean isSignOk = false;
    public boolean isSuccess = false;

    public Result(String result) {
        this.mPayResult = result;

        
    }

    static {
        mResultStatus = new HashMap<String, String>();
        mResultStatus.put("9000", "操作成功");
        mResultStatus.put("8000", "支付结果确认中");
        mResultStatus.put("4000", "系统异常");
        mResultStatus.put("4001", "数据格式不正确");
        mResultStatus.put("4003", "该用户绑定的支付宝账户被冻结或不允许支付");
        mResultStatus.put("4004", "该用户已解除绑定");
        mResultStatus.put("4005", "绑定失败或没有绑定");
        mResultStatus.put("4006", "订单支付失败");
        mResultStatus.put("4010", "重新绑定账户");
        mResultStatus.put("6000", "支付服务正在进行升级操作");
        mResultStatus.put("6001", "用户中途取消支付操作");
        mResultStatus.put("7001", "网页支付失败");
    }

    public void parseResult() {
        try {

            String[] resultParams = mPayResult.split(";");
            for (String resultParam : resultParams) {
                if (resultParam.startsWith("resultStatus")) {
                    resultStatus = gatValue(resultParam, "resultStatus");
                }
                if (resultParam.startsWith("result")) {
                    result = gatValue(resultParam, "result");
                }
                if (resultParam.startsWith("memo")) {
                    memo = gatValue(resultParam, "memo");
                }
            }
            isSignOk = checkSign(result);
            if (isSignOk){
               JSONObject j = string2JSON(result, "&");
               j.get("success");
               String success = j.getString("success");
               success = success.replace("\"", "");
               isSuccess = success.equals("true");
            }
        } catch (Exception e) {
            LogUtils.printExceptionStackTrace(e);
        }
    }

    //检查result是否是真实的
    private boolean checkSign(String result) {
        if (result == null || result.length() == 0){
            return false;
        }
        boolean retVal = false;
        try {
            JSONObject json = string2JSON(result, "&");
            int pos = result.indexOf("&sign=");
            int pos2 = result.indexOf("&sign_type=");
            String signContent = result.substring(0, pos);
            String signContent2 = result.substring(0, pos2);
            String signType = json.getString("sign_type");
            signType = signType.replace("\"", "");

            String sign = json.getString("sign");
            sign = sign.replace("\"", "");

            if (signType.equalsIgnoreCase("RSA")) {
                retVal = doCheck(signContent, sign, Config.ALIPAY_RSA_PUBLIC)
                        || doCheck(signContent2, sign, Config.ALIPAY_RSA_PUBLIC);
            }
        } catch (Exception e) {
            LogUtils.printExceptionStackTrace(e);
            LogUtils.i("Result", "Exception =" + e);
        }
        LogUtils.i("Result", "checkSign =" + retVal);
        return retVal;
    }


    public JSONObject string2JSON(String src, String split) {
        JSONObject json = new JSONObject();

        try {
            String[] arr = src.split(split);
            for (int i = 0; i < arr.length; i++) {
                String[] arrKey = arr[i].split("=");
                json.put(arrKey[0], arr[i].substring(arrKey[0].length() + 1));
            }
        } catch (Exception e) {
            LogUtils.printExceptionStackTrace(e);
        }

        return json;
    }

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public static boolean doCheck(String content, String sign, String publicKey){
        try{
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodeKey = Base64.decode(publicKey);
            PublicKey pubkey = keyFactory.generatePublic(new X509EncodedKeySpec(encodeKey));

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubkey);

            signature.update(content.getBytes("utf-8"));

            return signature.verify(Base64.decode(sign));
        }catch (Exception e){
            LogUtils.printExceptionStackTrace(e);
        }
        return false;
    }

    private String gatValue(String content, String key) {
        String prefix = key + "={";
        return content.substring(content.indexOf(prefix) + prefix.length(),
                content.lastIndexOf("}"));
    }

}
