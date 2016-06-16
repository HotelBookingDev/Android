package sf.hotel.com.data.utils;

import java.io.File;
import java.lang.reflect.Field;

import sf.hotel.com.data.config.HotelConstant;

/**
 * Created by 林其望 on 2016/6/16.
 */
public class HotelFileUtils {

    public static void clearImageDir(){
         deleteFileDir(new File(HotelConstant.TEMP_IMG_DIR));
    }

    private static void deleteFileDir(File file){
        if(isExit(file)){
            file.delete();
        }
    }

    private static boolean isExit(File file){
        boolean isExit=false;
        if (file!=null&&file.exists()&&file.isFile()){
            isExit=true;
        }
        return  isExit;
    }

    public static String getSize(File file){
        String size=null;
        if (isExit(file)){
            size=convertFileSize(file.length());
        }
        return size;
    }

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return null;
    }
}
