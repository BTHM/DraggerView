package win99.com.miaogu9.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.Bind;
import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseActivity;
import win99.com.miaogu9.catloadinglibrary.CatLoadingView;
import win99.com.miaogu9.domain.ModelHomeInfo;
import win99.com.miaogu9.util.Constant;
import win99.com.miaogu9.util.LogUtil;

public class WebActivity extends BaseActivity {


    @Bind(R.id.main_toolbar)
    Toolbar        mainToolbar;
    @Bind(R.id.web_webview)
    WebView        webWebview;
    @Bind(R.id.tool_textView)
    TextView       toolTextView;
    private String TAG=WebActivity.class.getName();
    private CatLoadingView mCatLoadingView;


    @Override
    public int initLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void setData() {
        loadWebView();

    }

    private void loadWebView() {
        webWebview.getSettings().setJavaScriptEnabled(true);//支持javascript
        webWebview.requestFocus();//触摸焦点起作用mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条
        webWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置允许js弹出alert对话框

        Intent intent = getIntent();
        String clazzName = intent.getStringExtra(Constant.SOURCE_FLAG);//
        String title=null;//标题
        String url=null;//web url
        switch(clazzName){
            case "AdapterHomeInfo"://垂直跑马灯,其传值bean  ModelHomeInfo
                ModelHomeInfo modelHomeInfo = (ModelHomeInfo) intent.getSerializableExtra(Constant.INTENT_DATA);
                title = modelHomeInfo.getTitle();
                url=modelHomeInfo.getUrl();
                break;
            case "FragmentHome":
                title = intent.getStringExtra(Constant.INTENT_DATA);
                url=intent.getStringExtra(Constant.HEADCONTENT_URL);
                break;
            default:
        }
        webWebview.loadUrl(url);
        webWebview.setWebChromeClient(new WebChromeClient());

        webWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtil.d(TAG,"web加载前");
                mCatLoadingView = new CatLoadingView();
                mCatLoadingView.show(getSupportFragmentManager(),null);
                //mCatLoadingView.show(getFragmentManager().beginTransaction(),null);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LogUtil.d(TAG,"web加载后");
                mCatLoadingView.dismiss();
            }
        });

    }

    @Override
    public void setListener() {

    }


}
