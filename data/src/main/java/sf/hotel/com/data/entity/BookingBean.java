package sf.hotel.com.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import sf.hotel.com.data.entity.netresult.hotel.room.HotelBean;
import sf.hotel.com.data.entity.netresult.hotel.room.RoomBean;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/21.
 */
public class BookingBean implements Parcelable{

    public final static String PRICE_S = "1";
    public final static String PRICE_D = "2";

    private HotelBean roomBean;

    private UserEntity userEntity;

    private int groupPos, childPos;

    private String priceType = PRICE_S;

    private SearchItem searchItem;

    public BookingBean() {
    }


    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public HotelBean getRoomBean() {
        return roomBean;
    }

    public void setRoomBean(HotelBean roomBean) {
        this.roomBean = roomBean;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public int getGroupPos() {
        return groupPos;
    }

    public void setGroupPos(int groupPos) {
        this.groupPos = groupPos;
    }

    public int getChildPos() {
        return childPos;
    }

    public void setChildPos(int childPos) {
        this.childPos = childPos;
    }

    public SearchItem getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(SearchItem searchItem) {
        this.searchItem = searchItem;
    }

    protected BookingBean(Parcel in) {
        roomBean = in.readParcelable(RoomBean.class.getClassLoader());
        groupPos = in.readInt();
        childPos = in.readInt();
        searchItem = in.readParcelable(SearchItem.class.getClassLoader());
    }

    public static final Creator<BookingBean> CREATOR = new Creator<BookingBean>() {
        @Override
        public BookingBean createFromParcel(Parcel in) {
            return new BookingBean(in);
        }

        @Override
        public BookingBean[] newArray(int size) {
            return new BookingBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(roomBean, flags);
        dest.writeInt(groupPos);
        dest.writeInt(childPos);
        dest.writeParcelable(searchItem, flags);
    }
}
