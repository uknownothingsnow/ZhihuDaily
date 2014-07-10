package app.brucelee.me.zhihudaily.interactor;


import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.ui.OnFetchedListener;

/**
 * Created by bruce on 7/9/14.
 */
public interface NewsDetailInteractor {
    public void fetch(long id, OnFetchedListener<NewsDetail> listener);
}
