package app.brucelee.me.zhihudaily.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.support.MyAsyncTask;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewsDetailActivity extends Activity {
    public static final String INTENT_NEWS_ID = "news_id";
    private static final String TAG = "NewsDetailActivity";
    private long newsId;
    @InjectView(R.id.wv_news_content) WebView webView;

    public static Intent newIntent(long newsId) {
        Intent intent = new Intent(ZhihuApplication.getInstance(), NewsDetailActivity.class);
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
        new NewsDetailTask().executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR);
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

    private void showNewsDetail(NewsDetail newsDetail) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        String newContent = getCustomerCss() + buildCssAndJs(newsDetail) + insertHeaderImage(newsDetail);
        webView.loadData(newContent, "text/html; charset=UTF-8", null);
    }

    private String getCustomerCss() {
        return "<style type=\"text/css\">\n" +
                "\t.headline .img-wrap {\n" +
                "\t\tposition: relative;\n" +
                "\t\tmax-height: 375px;\n" +
                "\t\toverflow: hidden;\n" +
                "\t}\n" +
                "\n" +
                "\t.headline .headline-title {\n" +
                "\t\tmargin: 20px 0;\n" +
                "\t\tbottom: 10px;\n" +
                "\t\tz-index: 1;\n" +
                "\t\tposition: absolute;\n" +
                "\t\tcolor: white;\n" +
                "\t\ttext-shadow: 0px 1px 2px rgba(0,0,0,0.3);\n" +
                "\t}\n" +
                "\n" +
                "\t.headline .img-source {\n" +
                "\t\tposition: absolute;\n" +
                "\t\tbottom: 10px;\n" +
                "\t\tz-index: 1;\n" +
                "\t\tfont-size: 12px;\n" +
                "\t\tcolor: rgba(255,255,255,.6);\n" +
                "\t\tright: 40px;\n" +
                "\t\ttext-shadow: 0px 1px 2px rgba(0,0,0,.3);\n" +
                "\t}\n" +
                "</style>";
    }

    private String insertHeaderImage(NewsDetail newsDetail) {
        String image = "<div class=\"headline\">\n" +
                "\n" +
                "<div class=\"img-wrap\">\n" +
                "<h1 class=\"headline-title\">" + newsDetail.title + "</h1>\n" +
                "\n" +
                "\n" +
                "<span class=\"img-source\">图片：Lily / CC BY</span>\n" +
                "\n" +
                "\n" +
                "<img src=\"" + newsDetail.image + "\" alt=\"\">\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</div>";
        if (newsDetail.body == null) {
            return "";
        }
        return newsDetail.body.replaceFirst("<div class=\"img-place-holder\"></div>", image);
    }

    private String buildCssAndJs(NewsDetail newsDetail) {
        StringBuilder sb = new StringBuilder();
        for (String css : newsDetail.css) {
            sb.append("<link rel=\"stylesheet\" href=\"").append(css).append("\" />");
        }

        for (String js : newsDetail.js) {
            sb.append("<script src=\"").append(js).append("\" />");
        }
        return sb.toString();
    }

    private class NewsDetailTask extends MyAsyncTask<Void, Void, NewsDetail> {

        @Override
        protected NewsDetail doInBackground(Void... params) {
            ZhihuService service = ZhihuApplication.getInstance().getRestAdapter().create(ZhihuService.class);
            return service.getNewsDetail(newsId);
        }

        @Override
        protected void onPostExecute(NewsDetail newsDetail) {
            if (null != newsDetail) {
                showNewsDetail(newsDetail);
            }
        }
    }
}
