package app.brucelee.me.zhihudaily.interactor;

import android.os.Handler;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.ui.OnFetchedListener;

/**
 * Created by bruce on 7/8/14.
 */
public class NewsListInteractorImpl implements NewsListInteractor {

    ZhihuService service;
    final Handler handler = new Handler();

    public NewsListInteractorImpl(ZhihuService service) {
        this.service = service;
    }

    @Override
    public void fetchItems(final OnFetchedListener<LatestNewsList> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final LatestNewsList latestNews = service.getLatestNewsList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFetched(latestNews);
                    }
                });
            }
        }).start();
    }
}
