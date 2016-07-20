package sf.hotel.com.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/12.
 */
public class SearchItem implements Parcelable{


    public Date inTime = new Date(System.currentTimeMillis());
    public Date outTime = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000);
    public CityBean cityBean = new CityBean();
    public int adultCount = 0;
    public int childCount = 0;


    public SearchItem(){}

    protected SearchItem(Parcel in) {
        cityBean = in.readParcelable(CityBean.class.getClassLoader());
        adultCount = in.readInt();
        childCount = in.readInt();
    }


    public String getSimpleInTime(){
        return getSimpleDate(inTime);
    }

    public String getSimpleOutTime(){
        return getSimpleDate(outTime);
    }
    private String getSimpleDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    public static final Creator<SearchItem> CREATOR = new Creator<SearchItem>() {
        @Override
        public SearchItem createFromParcel(Parcel in) {
            return new SearchItem(in);
        }

        @Override
        public SearchItem[] newArray(int size) {
            return new SearchItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(cityBean, flags);
        dest.writeInt(adultCount);
        dest.writeInt(childCount);
    }
}
