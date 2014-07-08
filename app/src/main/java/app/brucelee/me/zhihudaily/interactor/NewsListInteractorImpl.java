package app.brucelee.me.zhihudaily.interactor;

import android.os.Handler;

import javax.inject.Inject;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.ui.newsList.OnFetchedListener;

/**
 * Created by bruce on 7/8/14.
 */
public class NewsListInteractorImpl implements NewsListInteractor {

    ZhihuService service;

    public NewsListInteractorImpl(ZhihuService service) {
        this.service = service;
    }

    @Override
    public void fetchItems(final OnFetchedListener listener) {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final LatestNewsList latestNews = service.getLatestNewsList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFetched(latestNews.news, latestNews.topNews);
                    }
                });
            }
        }).start();
    }
}
