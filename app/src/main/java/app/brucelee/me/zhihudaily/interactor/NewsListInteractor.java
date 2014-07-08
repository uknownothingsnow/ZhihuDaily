package app.brucelee.me.zhihudaily.interactor;

import app.brucelee.me.zhihudaily.ui.newsList.OnFetchedListener;

/**
 * Created by bruce on 7/8/14.
 */
public interface NewsListInteractor {
    public void fetchItems(final OnFetchedListener listener);
}
