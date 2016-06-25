package sf.hotel.com.data.utils;

import android.util.Log;

import com.qiniu.android.storage.UploadManager;

import java.io.File;

import sf.hotel.com.data.entity.netresult.TokenResult;

/**
 * Created by 林其望
 * data：2016/6/22
 * email: 1105896230@qq.com
 */
public class QNUpFileUtils {
    public static void upFileByQN(File file, TokenResult mTokenResult) {
        if (mTokenResult == null) return;
        UploadManager uploadManager = new UploadManager();
        String data = file.getPath();
        String key = mTokenResult.getImageUrl();
        String token = mTokenResult.getToken();
        uploadManager.put(data, key, token, (key1, info, res) -> {
            //  res 包含hash、key等信息，具体字段取决于上传策略的设置。
            Log.i("qiniu", key1 + ",\r\n " + info + ",\r\n " + res);
        }, null);
    }
}
