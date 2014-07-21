package app.brucelee.me.zhihudaily.ui.newsDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.support.MyAsyncTask;
import app.brucelee.me.zhihudaily.ui.BaseActivity;
import app.brucelee.me.zhihudaily.ui.IPresenter;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewsDetailActivity extends BaseActivity implements NewsDetailView {
    public static final String INTENT_NEWS_ID = "news_id";
    private static final String TAG = "NewsDetailActivity";
    private long newsId;
    @InjectView(R.id.wv_news_content) WebView webView;
    @Inject NewsDetailPresenter presenter;

    public static Intent newIntent(Context context, long newsId) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(INTENT_NEWS_ID, newsId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.inject(this);
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            newsId = extras.getLong(INTENT_NEWS_ID);
        }
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
    }

    @Override
    protected IPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new NewsDetailModule(this));
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    @Override
    public long getId() {
        return newsId;
    }

}
