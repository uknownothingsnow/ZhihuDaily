package app.brucelee.me.zhihudaily.interactor;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.ui.OnFirstLoadListener;
import app.brucelee.me.zhihudaily.ui.OnMoreLoadedListener;
import rx.Subscriber;
import rx.android.observables.AndroidObservable;

/**
 * Created by bruce on 7/8/14.
 */
public class NewsListInteractorImpl implements NewsListInteractor {

    private final String TAG = "NewsListInteractorImpl";
    private ZhihuService service;
    final Handler handler = new Handler();

    public NewsListInteractorImpl(ZhihuService service) {
        this.service = service;
    }

    @Override
    public void fetchItems(final OnFirstLoadListener<LatestNewsList> listener) {
        new Thread(() -> {
            final LatestNewsList latestNews = service.getLatestNewsList();
            handler.post(() -> listener.onFirstLoad(latestNews));
        }).start();
    }

    @Override
    public void loadMore(OnMoreLoadedListener<LatestNewsList> listener, final Fragment fragment, String date) {
        Log.d(TAG, "loadMore date: " + date);
        AndroidObservable.bindFragment(fragment, service.loadMoreNews(date)).subscribe(new Subscriber<LatestNewsList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(LatestNewsList latestNewsList) {
                listener.onMoreLoaded(latestNewsList);
            }
        });
    }
}
