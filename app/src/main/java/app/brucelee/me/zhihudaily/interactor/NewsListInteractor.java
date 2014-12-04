package app.brucelee.me.zhihudaily.interactor;

import android.support.v4.app.Fragment;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.ui.OnFirstLoadListener;
import app.brucelee.me.zhihudaily.ui.OnMoreLoadedListener;

/**
 * Created by bruce on 7/8/14.
 */
public interface NewsListInteractor {
    public void fetchItems(final OnFirstLoadListener<LatestNewsList> listener);

    public void loadMore(final OnMoreLoadedListener<LatestNewsList> listener, final Fragment fragment, String date);
}
