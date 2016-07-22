package sf.hotel.com.hotel_client.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
import sf.hotel.com.data.entity.Order;
import sf.hotel.com.data.utils.TimeUtils;
import sf.hotel.com.hotel_client.R;

/**
 * Created by 林其望
 * data：2016/7/4
 * email: 1105896230@qq.com
 */
public class UserOrderAdapter extends RecyclerViewBaseAdapter {
    List<Order> mOrders;
    UserOrderClick mUserOrderClick;

    public UserOrderAdapter(Context context, List<Order> mOrders) {
        super(context);
        this.mOrders = mOrders;
        setCount(mOrders.size());
    }

    public void setmUserOrderClick(UserOrderClick mUserOrderClick) {
        this.mUserOrderClick = mUserOrderClick;
    }

    public void setOrders(List<Order> orders) {
        if (orders == null) return;
        mOrders.clear();
        mOrders.addAll(orders);
        setCount(mOrders.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Order order = mOrders.get(position);
        if (order.isClosed()) {
            viewHolder.cancleBtn.setVisibility(View.GONE);
        } else {
            viewHolder.cancleBtn.setVisibility(View.VISIBLE);
        }
        viewHolder.cancleBtn.setOnClickListener(v -> {
            if (mUserOrderClick != null)
                mUserOrderClick.click(order);
        });
        setText(order.getHotel_name(), viewHolder.mHotelName);
        setMoney(order, viewHolder.mRoomMoney);
        setText(order.getOrder_num() + "", viewHolder.mBookingNumber);
        setTime(order, viewHolder.mCheckTime);
        setText(order.getRoom_name() + "—" + getBreakfast(order.getBreakfast()), viewHolder.mRoomType);
    }

    private String getBreakfast(int breakfast) {
        String str = "";
        if (breakfast == Order.BREAFKFAST_DOUBLE) {
            str = "含双早";
        } else if (breakfast == Order.BREAFKFAST_ONE) {
            str = "含单早";
        } else if (breakfast == Order.BREAFKFAST_NOT) {
            str = "无早";
        }
        return str;
    }

    private void setTime(Order order, TextView mCheckTime) {
        String[] checkinsplit = order.getCheckin_time().split("-");
        String[] checkoutsplit = order.getCheckout_time().split("-");
        StringBuilder builder = new StringBuilder();
        builder.append(getTime(checkinsplit)).append("—").
                append(getTime(checkoutsplit)).append(" ").
                append("共").
                append(TimeUtils.getTimeDifference(order.getCheckin_time(), order.getCheckout_time())).
                append("晚");
        mCheckTime.setText(builder.toString());
    }

    private void setMoney(Order order, TextView mRoomMoney) {
        StringBuilder builder = new StringBuilder();
        builder.append(order.getTotal_front_prices());
        builder.append(" points");
        builder.append("+");
        builder.append(order.getTotal_need_points());
        builder.append(" CNY");
        mRoomMoney.setText(builder.toString());
    }

    private void setText(String text, TextView view) {
        if (!TextUtils.isEmpty(text)) {
            view.setText(text);
        }
    }


    private String getTime(String[] split) {
        StringBuilder builder = new StringBuilder();
        builder.append(split[1]);
        builder.append("月");
        builder.append(split[2]);
        builder.append("日");
        return builder.toString();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //        ImageView mHotelIcon;
        TextView mHotelName;
        TextView mRoomMoney;
        TextView mBookingNumber;
        TextView mRoomType;
        TextView mCheckTime;
        FancyButton cancleBtn;


        public ViewHolder(View itemView) {
            super(itemView);
            cancleBtn = (FancyButton) itemView.findViewById(R.id.cancle_order_btn);
//            mHotelIcon = (ImageView) itemView.findViewById(R.id.iv_hotel);
            mHotelName = (TextView) itemView.findViewById(R.id.tv_hotel);
            mRoomMoney = (TextView) itemView.findViewById(R.id.tv_total_change);
            mBookingNumber = (TextView) itemView.findViewById(R.id.tv_booking_number_change);
            mCheckTime = (TextView) itemView.findViewById(R.id.tv_check_time_change);
            mRoomType = (TextView) itemView.findViewById(R.id.tv_room_type_change);
        }
    }

    public interface UserOrderClick {
        void click(Order order);
    }
}
