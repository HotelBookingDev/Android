package sf.hotel.com.data.utils;

import android.util.Log;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;

import sf.hotel.com.data.entity.netresult.TokenResult;

/**
 * Created by 林其望 on 2016/6/22.
 */
public class QNUpFileUtils {
    public static void upFileByQN(File file, TokenResult mTokenResult) {
        UploadManager uploadManager = new UploadManager();
        String data = file.getPath();
        String key = null;
        String token = mTokenResult.getToken();
        uploadManager.put(data, key, token, (key1, info, res) -> {
            //  res 包含hash、key等信息，具体字段取决于上传策略的设置。
            Log.i("qiniu", key1 + ",\r\n " + info + ",\r\n " + res);
        }, null);
    }
}
