package app.brucelee.me.zhihudaily.ui.newsDetail;

import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.support.MyAsyncTask;
import app.brucelee.me.zhihudaily.ui.OnFirstLoadListener;

/**
 * Created by bruce on 7/9/14.
 */
public class NewsDetailTask extends MyAsyncTask<Void, Void, NewsDetail> {
    private final long newsId;
    private final OnFirstLoadListener<NewsDetail> listener;
    private final ZhihuService service;

    public NewsDetailTask(final ZhihuService service, final long newsId, final OnFirstLoadListener<NewsDetail> listener) {
        this.service = service;
        this.newsId = newsId;
        this.listener = listener;
    }

    @Override
    protected NewsDetail doInBackground(Void... params) {
        return service.getNewsDetail(newsId);
    }

    @Override
    protected void onPostExecute(NewsDetail newsDetail) {
        if (null != newsDetail) {
            listener.onFirstLoad(newsDetail);
        }
    }
}
