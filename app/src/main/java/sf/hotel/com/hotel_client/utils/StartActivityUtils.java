package sf.hotel.com.hotel_client.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import java.net.URISyntaxException;

import sf.hotel.com.data.utils.LogUtils;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/7/5.
 */
public class StartActivityUtils {

    public static boolean startLBS(Activity c,
                                String location,
                                String title,
                                String content){
        Intent intent = null;

        String str = "intent://map/marker" +
                "?location=40.047669,116.313082" +
                "&title=香格里拉" +
                "&content=百度奎科大厦" +
                "&name=28" +
                "&src=sf.hotel.com.hotel_client" +
                "#Intent;" +
                "scheme=bdapp" +
                "package=com.baidu.BaiduMap" +
                ";end";
        try {
            intent = Intent.getIntent(str);
            //调起百度地图客户端（Android）展示上海市"28"路公交车的检索结果
            c.startActivity(intent);   //启动调用
            return true;
        } catch (URISyntaxException
                | ActivityNotFoundException e) {
            LogUtils.printExceptionStackTrace(e);
            return false;
        }
    }


    public static void startPhone(Activity c, String phone){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ phone));
        c.startActivity(intent);
    }

}
