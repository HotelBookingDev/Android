package sf.hotel.com.data.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 林其望
 * data：2016/6/29
 * email: 1105896230@qq.com
 */
public class TimeUtils {

    public static long getCurrentTimes() {
        return System.currentTimeMillis();
    }

    public static long getTimeDifference(String checkIn, String checkOut) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long days = 0;
        try {

            Date d1 = df.parse(checkIn);

            Date d2 = df.parse(checkOut);
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
        }
        return days;
    }

    public static long TimeAdapter(String modified) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss:SSSZZZ";
        DateFormat df = new SimpleDateFormat(pattern);
        long time = 0;
        try {
            Date parse = df.parse(modified);
            time = parse.getTime();
        } catch (Exception e) {
        }
        return time;
    }
}
