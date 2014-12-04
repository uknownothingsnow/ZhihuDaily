package app.brucelee.me.zhihudaily.interactor;


import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.ui.OnFirstLoadListener;
import rx.Observer;
import rx.Subscription;

/**
 * Created by bruce on 7/9/14.
 */
public interface NewsDetailInteractor {
    public void fetch(long id, OnFirstLoadListener<NewsDetail> listener);

    public Subscription doFetch(final long id, Observer<NewsDetail> observer);
}
