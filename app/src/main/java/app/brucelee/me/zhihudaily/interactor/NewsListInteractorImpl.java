package app.brucelee.me.zhihudaily.interactor;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.ui.OnFetchedListener;
import app.brucelee.me.zhihudaily.ui.OnMoreLoadedListener;
import rx.Subscriber;
import rx.android.observables.AndroidObservable;

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
        new Thread(() -> {
            final LatestNewsList latestNews = service.getLatestNewsList();
            handler.post(() -> listener.onFetched(latestNews));
        }).start();
    }

    @Override
    public void loadMore(OnMoreLoadedListener<LatestNewsList> listener, final Fragment fragment, String date) {
        AndroidObservable.bindFragment(fragment, service.loadMoreNews(date)).subscribe(new Subscriber<LatestNewsList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LatestNewsList latestNewsList) {
                listener.onMoreLoaded(latestNewsList);
            }
        });
    }
}
