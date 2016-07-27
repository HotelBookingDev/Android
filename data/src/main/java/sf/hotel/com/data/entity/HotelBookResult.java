package sf.hotel.com.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/12.
 */
public class HotelBookResult implements Parcelable{

    /**
     * total_front_prices : 0
     * shipping_status : 0
     * people_count : 1
     * total_need_points : 0
     * room_name : 商务爆炸大床
     * breakfast : 1
     * checkin_time : 2016-07-31
     * hotel_name : 无敌酒店
     * process_state : 1
     * order : 100216072717242
     * payment_status : 1
     * modified_by : null
     * room_count : 1
     * customer : 4
     * created : 2016-07-27T17:47:56.773495
     * modified : 2016-07-27T17:47:56.792419
     * seller : 3
     * checkout_time : 2016-08-01
     * request_notes : 需要wifi
     * comment : null
     * deleted : false
     * closed : false
     * product : 2
     * guests : null
     */

    private int total_front_prices;
    private int shipping_status;
    private int people_count;
    private int total_need_points;
    private String room_name;
    private int breakfast;
    private String checkin_time;
    private String hotel_name;
    private int process_state;
    private String order;
    private int payment_status;
    private Object modified_by;
    private int room_count;
    private int customer;
    private String created;
    private String modified;
    private int seller;
    private String checkout_time;
    private String request_notes;
    private Object comment;
    private boolean deleted;
    private boolean closed;
    private int product;
    private Object guests;

    protected HotelBookResult(Parcel in) {
        total_front_prices = in.readInt();
        shipping_status = in.readInt();
        people_count = in.readInt();
        total_need_points = in.readInt();
        room_name = in.readString();
        breakfast = in.readInt();
        checkin_time = in.readString();
        hotel_name = in.readString();
        process_state = in.readInt();
        order = in.readString();
        payment_status = in.readInt();
        room_count = in.readInt();
        customer = in.readInt();
        created = in.readString();
        modified = in.readString();
        seller = in.readInt();
        checkout_time = in.readString();
        request_notes = in.readString();
        deleted = in.readByte() != 0;
        closed = in.readByte() != 0;
        product = in.readInt();
    }

    public static final Creator<HotelBookResult> CREATOR = new Creator<HotelBookResult>() {
        @Override
        public HotelBookResult createFromParcel(Parcel in) {
            return new HotelBookResult(in);
        }

        @Override
        public HotelBookResult[] newArray(int size) {
            return new HotelBookResult[size];
        }
    };

    public int getTotal_front_prices() {
        return total_front_prices;
    }

    public void setTotal_front_prices(int total_front_prices) {
        this.total_front_prices = total_front_prices;
    }

    public int getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(int shipping_status) {
        this.shipping_status = shipping_status;
    }

    public int getPeople_count() {
        return people_count;
    }

    public void setPeople_count(int people_count) {
        this.people_count = people_count;
    }

    public int getTotal_need_points() {
        return total_need_points;
    }

    public void setTotal_need_points(int total_need_points) {
        this.total_need_points = total_need_points;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public String getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(String checkin_time) {
        this.checkin_time = checkin_time;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public int getProcess_state() {
        return process_state;
    }

    public void setProcess_state(int process_state) {
        this.process_state = process_state;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }

    public Object getModified_by() {
        return modified_by;
    }

    public void setModified_by(Object modified_by) {
        this.modified_by = modified_by;
    }

    public int getRoom_count() {
        return room_count;
    }

    public void setRoom_count(int room_count) {
        this.room_count = room_count;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public String getCheckout_time() {
        return checkout_time;
    }

    public void setCheckout_time(String checkout_time) {
        this.checkout_time = checkout_time;
    }

    public String getRequest_notes() {
        return request_notes;
    }

    public void setRequest_notes(String request_notes) {
        this.request_notes = request_notes;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public Object getGuests() {
        return guests;
    }

    public void setGuests(Object guests) {
        this.guests = guests;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total_front_prices);
        dest.writeInt(shipping_status);
        dest.writeInt(people_count);
        dest.writeInt(total_need_points);
        dest.writeString(room_name);
        dest.writeInt(breakfast);
        dest.writeString(checkin_time);
        dest.writeString(hotel_name);
        dest.writeInt(process_state);
        dest.writeString(order);
        dest.writeInt(payment_status);
        dest.writeInt(room_count);
        dest.writeInt(customer);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeInt(seller);
        dest.writeString(checkout_time);
        dest.writeString(request_notes);
        dest.writeByte((byte) (deleted ? 1 : 0));
        dest.writeByte((byte) (closed ? 1 : 0));
        dest.writeInt(product);
    }
}
