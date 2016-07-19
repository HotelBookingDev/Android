package sf.hotel.com.data.entity.netresult.pay;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/19.
 */
public class PayResult implements Parcelable{
    /**
     * url : https://openapi.alipay.com/gateway.do?seller_id=chaomengshidai%40agesd.com&payment_type=1&return_url=http%3A%2F%2Fwww.agesd.com%2Falipay%2Freturn%2F&service=mobile.securitypay.pay&subject=%E7%A7%AF%E5%88%86%E5%85%85%E5%80%BC&total_fee=100&sign=WkYzS2Y0djNoUWV3KzdrWVFLcEpINTFoa01NNTFRYUNHQWtudjhFTGZsVTJ5cmM5cnBrMUEybm9CMW40R0swam9OTnFhNUJ5Sko5allzSk5QQi9pTjFXNXhpa3J5aks4NXlOeTYvM2RYT3RGSldKYzFLeXRpU212Z25yREZhOWxxT05EM2NibksyMW9jQ1FvamRKa3JSWTlqcStwNTVOVUhGNGpIK2xHVGtBPQ%3D%3D&body=%E5%95%86%E5%93%81%E8%AF%A6%E6%83%85&out_trade_no=e4bbf2d8-e31c-4b7b-b478-f35ce2e89c8c&partner=2088421443875084&sign_type=RSA&_input_charset=utf-8&notify_url=http%3A%2F%2Fagesd.com%2Falipay%2Fnotify%2F
     */

    private String url;

    public PayResult() {
    }


    protected PayResult(Parcel in) {
        url = in.readString();
    }

    public static final Creator<PayResult> CREATOR = new Creator<PayResult>() {
        @Override
        public PayResult createFromParcel(Parcel in) {
            return new PayResult(in);
        }

        @Override
        public PayResult[] newArray(int size) {
            return new PayResult[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }
}
