package sf.hotel.com.data.utils;

import sf.hotel.com.data.entity.WebViewsModel;

/**
 * Created by 林其望
 * data：2016/6/30
 * email: 1105896230@qq.com
 */
public class WebViewModelFactory {
    public static int BAIDU = 0x1;
    public static final String BAIDU_KYE_URL = "https://www.baidu.com";
    public static final String BAIDU_KEY_TITLE = "百度";

    public static WebViewsModel getModel(int type) {
        WebViewsModel model = null;
        if (BAIDU == type) {
            model = new WebViewsModel(BAIDU_KYE_URL, BAIDU_KEY_TITLE);
        }
        return model;
    }
}
