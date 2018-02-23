package com.mtianyan.mtianyan001.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.base.RxBaseActivity;
import com.mtianyan.mtianyan001.utils.ClipboardUtil;
import com.mtianyan.mtianyan001.utils.ConstantUtil;
import com.mtianyan.mtianyan001.utils.ToastUtils;
import com.mtianyan.mtianyan001.widget.CircleProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hcc on 16/8/7 14:12
 * 100332338@qq.com
 * <p/>
 * 浏览器界面
 */
public class BrowserActivity extends RxBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.circle_progress)
    CircleProgressView progressBar;

    @BindView(R.id.webView)
    WebView mWebView;

    private final Handler mHandler = new Handler();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    private String url, mTitle;

    private WebViewClientBase webViewClient = new WebViewClientBase();


    @Override
    public int getLayoutId() {

        return R.layout.activity_web;
    }


    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(ConstantUtil.EXTRA_URL);
            mTitle = intent.getStringExtra(ConstantUtil.EXTRA_TITLE);
        }

        setupWebView();
    }


    @Override
    public void initToolBar() {
//    textView = (TextView) findViewById(R.id.tv_title);
//
//    textView.setText(TextUtils.isEmpty(mTitle) ? "详情" : mTitle);
//    setSupportActionBar(mToolbar);
//    ActionBar supportActionBar = getSupportActionBar();
//    if (supportActionBar != null) {
//      supportActionBar.setDisplayHomeAsUpEnabled(true);
//    }
//    getSupportActionBar().setTitle(null);
        //设置标题


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_share:
                share();
                break;

            case R.id.menu_open:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.menu_copy:
                ClipboardUtil.setText(BrowserActivity.this, url);
                ToastUtils.ShortToast("已复制");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void share() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + url);
        startActivity(Intent.createChooser(intent, mTitle));
    }


    @Override
    public void onBackPressed() {

        if (mWebView.canGoBack() && mWebView.copyBackForwardList().getSize() > 0
                && !mWebView.getUrl().equals(mWebView.copyBackForwardList()
                .getItemAtIndex(0).getOriginalUrl())) {
            mWebView.goBack();
        } else {
            finish();
        }
    }


    public static void launch(Activity activity, String url, String title) {

        Intent intent = new Intent(activity, BrowserActivity.class);
        intent.putExtra(ConstantUtil.EXTRA_URL, url);
        intent.putExtra(ConstantUtil.EXTRA_TITLE, title);
        activity.startActivity(intent);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {

        progressBar.spin();

        final WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.getSettings().setBlockNetworkImage(true);
        mWebView.setWebViewClient(webViewClient);
        mWebView.requestFocus(View.FOCUS_DOWN);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {

                AlertDialog.Builder b2 = new AlertDialog
                        .Builder(BrowserActivity.this)
                        .setTitle(R.string.app_name)
                        .setMessage(message)
                        .setPositiveButton("确定", (dialog, which) -> result.confirm());

                b2.setCancelable(false);
                b2.create();
                b2.show();
                return true;
            }
        });
        mWebView.loadUrl(url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        toolbarTitle.setText(mTitle);
        mToolbar.setNavigationIcon(R.drawable.action_button_back_pressed_light);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }


    public class WebViewClientBase extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }


        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            progressBar.stopSpinning();
            mWebView.getSettings().setBlockNetworkImage(false);
        }


        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

            super.onReceivedError(view, request, error);
            String errorHtml = "<html><body><h2>找不到网页</h2></body></html>";
            view.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null);
        }
    }


    @Override
    protected void onPause() {

        mWebView.reload();
        super.onPause();
    }


    @Override
    protected void onDestroy() {

        mWebView.destroy();
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
