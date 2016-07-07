package sf.hotel.com.hotel_client.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import sf.hotel.com.hotel_client.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/7.
 */
public class CustomTimerView extends RelativeLayout{

    HotelSearchTitle mStartView, mEndView;

    TextView mAllTime;


    Date[] dates = new Date[2];

    public CustomTimerView(Context context) {
        this(context, null);
    }

    public CustomTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        dates[0] = date;
        dates[1] = date;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_timer_view, this);

        mStartView = (HotelSearchTitle) view.findViewById(R.id.custom_timer_start);
        mEndView = (HotelSearchTitle) view.findViewById(R.id.custom_timer_end);
        mAllTime = (TextView) view.findViewById(R.id.custom_timer_all);
        setTimer(dates[0], dates[1]);
    }


    public void setTimer(Date start, Date end){
        dates[0] = start;
        dates[1] = end;

        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");

        SimpleDateFormat formatE = new SimpleDateFormat("E");

        String startTime = format.format(start);
        String endTime = format.format(end);

        String startWeek = formatE.format(start);
        String endWeek = formatE.format(end);

        int time = (int) ((end.getTime() - start.getTime()) / (1000 * 60 * 60 *24));

        mStartView.setContentText(startTime);
        mEndView.setContentText(endTime);

        mStartView.setDesText(startWeek);
        mEndView.setDesText(endWeek);

        mAllTime.setText(String.valueOf(time) + "晚");
    }

    public Date[] getTimer(){
       return dates;
    }

}
