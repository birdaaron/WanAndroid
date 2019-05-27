package www.birdaaron.com.wanandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import www.birdaaron.com.wanandroid.R;

/**
 * Web显示界面
 */
public class WebActivity extends AppCompatActivity
{
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initWeb();
    }
    private void initView()
    {
        mWebView = findViewById(R.id.web_wv_container);
    }
    private void initWeb()
    {
        Intent intent = getIntent();
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(intent.getStringExtra("webURL"));
    }
}
