package app.brucelee.me.zhihudaily.interactor;

import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.support.MyAsyncTask;
import app.brucelee.me.zhihudaily.ui.newsDetail.NewsDetailTask;
import app.brucelee.me.zhihudaily.ui.OnFetchedListener;
import retrofit.RestAdapter;

/**
 * Created by bruce on 7/9/14.
 */
public class NewsDetailInteractorImpl implements NewsDetailInteractor {
    ZhihuService service;

    public NewsDetailInteractorImpl(ZhihuService service) {
        this.service = service;
    }

    @Override
    public void fetch(final long id, final OnFetchedListener<NewsDetail> listener) {
        new NewsDetailTask(service, id, listener).executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR);
    }
}
