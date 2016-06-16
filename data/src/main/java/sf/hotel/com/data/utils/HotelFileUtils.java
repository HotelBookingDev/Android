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
}
