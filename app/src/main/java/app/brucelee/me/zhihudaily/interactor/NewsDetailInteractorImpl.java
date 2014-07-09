package app.brucelee.me.zhihudaily.interactor;

import android.os.Handler;
import android.util.Log;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.support.MyAsyncTask;
import app.brucelee.me.zhihudaily.ui.newsDetail.NewsDetailTask;
import app.brucelee.me.zhihudaily.ui.newsDetail.OnFetchedListener;

/**
 * Created by bruce on 7/9/14.
 */
public class NewsDetailInteractorImpl implements NewsDetailInteractor {
    ZhihuService service;

    public NewsDetailInteractorImpl(ZhihuService service) {
        this.service = service;
    }

    @Override
    public void fetch(final long id, final OnFetchedListener listener) {
        new NewsDetailTask(id, listener).executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR);
    }
}
