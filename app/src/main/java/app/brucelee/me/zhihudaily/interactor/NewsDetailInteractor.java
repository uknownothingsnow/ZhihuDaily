package app.brucelee.me.zhihudaily.interactor;


import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.ui.OnFetchedListener;
import rx.Observer;
import rx.Subscription;

/**
 * Created by bruce on 7/9/14.
 */
public interface NewsDetailInteractor {
    public void fetch(long id, OnFetchedListener<NewsDetail> listener);

    public Subscription doFetch(final long id, Observer<NewsDetail> observer);
}
