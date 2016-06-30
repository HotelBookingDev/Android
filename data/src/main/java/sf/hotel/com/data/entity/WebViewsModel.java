package sf.hotel.com.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 林其望
 * data：2016/6/30
 * email: 1105896230@qq.com
 */
public class WebViewsModel implements Parcelable{
    private String url;
    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WebViewsModel(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public WebViewsModel(Parcel in) {
        url = in.readString();
        title = in.readString();
    }

    public static final Creator<WebViewsModel> CREATOR = new Creator<WebViewsModel>() {
        @Override
        public WebViewsModel createFromParcel(Parcel in) {
            return new WebViewsModel(in);
        }

        @Override
        public WebViewsModel[] newArray(int size) {
            return new WebViewsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(title);
    }
}
