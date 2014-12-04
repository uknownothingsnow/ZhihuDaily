package app.brucelee.me.zhihudaily.interactor;

import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.support.MyAsyncTask;
import app.brucelee.me.zhihudaily.ui.OnFirstLoadListener;
import app.brucelee.me.zhihudaily.ui.newsDetail.NewsDetailTask;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by bruce on 7/9/14.
 */
public class NewsDetailInteractorImpl implements NewsDetailInteractor {
    ZhihuService service;

    public NewsDetailInteractorImpl(ZhihuService service) {
        this.service = service;
    }

    @Override
    public void fetch(final long id, final OnFirstLoadListener<NewsDetail> listener) {
        new NewsDetailTask(service, id, listener).executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public Subscription doFetch(final long id, final Observer<NewsDetail> observer) {
        PublishSubject<NewsDetail> newsDetailRequest = PublishSubject.create();
        Subscription subscription = newsDetailRequest.subscribe(observer);
        service.getNewsDetailObservable(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsDetailRequest);

        return subscription;
    }
}
