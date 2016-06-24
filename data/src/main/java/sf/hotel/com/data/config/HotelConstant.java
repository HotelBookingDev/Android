package sf.hotel.com.data.config;

import android.os.Environment;

/**
 * Created by FMT on 2016/6/13:13:33
 * EMAILE 1105896230@qq.com.
 */
public class HotelConstant {

    //在Application中初始化文件夹Hotel_Dir
    public static final String HOTEL_DIR = Environment.getExternalStorageDirectory() + "/Hotel/";

    public static final String TEMP_IMG_DIR = Environment.getExternalStorageDirectory() +
            "/Hotel/image/";
    public static final int TEMP_IMG_SIZE = 1024 * 1024 * 10;
}
