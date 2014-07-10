package app.brucelee.me.zhihudaily.interactor;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.ui.OnFetchedListener;

/**
 * Created by bruce on 7/8/14.
 */
public interface NewsListInteractor {
    public void fetchItems(final OnFetchedListener<LatestNewsList> listener);
}
