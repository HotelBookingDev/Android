package sf.hotel.com.hotel_client.view.activity;

import android.os.Bundle;
import android.webkit.ClientCertRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import sf.hotel.com.data.entity.WebViewsModel;
import sf.hotel.com.hotel_client.R;
import sf.hotel.com.hotel_client.view.custom.HotelTitleView;

public class WebViewActivity extends BaseActivity {

    private WebViewsModel model;
    public static final String KEY = "model";

    @BindView(R.id.view_title)
    public HotelTitleView title;
    @BindView(R.id.mWebview)
    public WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        model = getIntent().getParcelableExtra(KEY);
        initViews();
    }

    public void onPause() {
        super.onPause();
        mWebview.onPause();
    }

    public void onResume() {
        super.onResume();
        mWebview.onResume();
    }

    private void initViews() {
        title.addLeftClick(v -> finish());

        if (model != null) {
            mWebview.loadUrl(model.getUrl());
            mWebview.getSettings().setSupportZoom(true);// 支持缩放
            mWebview.getSettings().setBuiltInZoomControls(true);// 支持缩放
            mWebview.getSettings().setJavaScriptEnabled(true);// 支持js
            mWebview.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
                    //handler.cancel(); 默认的处理方式，WebView变成空白页
                    //handler.process();接受证书
                    //handleMessage(Message msg); 其他处理
                }
            });
            title.setTitle(model.getTitle());
        }
    }
}
