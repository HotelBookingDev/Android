package sf.hotel.com.data.entity.netresult;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 林其望
 * data：2016/6/22
 * email: 1105896230@qq.com
 */
public class TokenResult {
    public String getToken() {
        return token;
    }

    @SerializedName("upload_token")
    private String token;

    public String getImageUrl() {
        return imageUrl;
    }

    @SerializedName("imageUrl")

    private String imageUrl;
}
