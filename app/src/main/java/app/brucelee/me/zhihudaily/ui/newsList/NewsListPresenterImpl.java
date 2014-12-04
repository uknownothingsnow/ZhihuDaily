package app.brucelee.me.zhihudaily.ui.newsList;

import android.app.Activity;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.interactor.NewsListInteractor;
import app.brucelee.me.zhihudaily.ui.OnMoreLoadedListener;
import app.brucelee.me.zhihudaily.ui.newsDetail.NewsDetailActivity;
import app.brucelee.me.zhihudaily.ui.OnFirstLoadListener;

/**
 * Created by bruce on 7/8/14.
 */
public class NewsListPresenterImpl implements NewsListPresenter, OnFirstLoadListener<LatestNewsList>, OnMoreLoadedListener<LatestNewsList> {

    private NewsListView view;
    private Activity activity;
    private NewsListInteractor interactor;
    private String lastDate = "";

    public NewsListPresenterImpl(NewsListView view, NewsListInteractor interactor) {
        this.view = view;
        this.activity = ((NewsListFragment) view).getActivity();
        this.interactor = interactor;
        interactor.fetchItems(this);
    }

    @Override
    public void onListItemClick(final int position) {
        News news = view.getNewsAdapter().getItem(position);
        activity.startActivity(NewsDetailActivity.newIntent(view.getContext(), news.id));
    }

    @Override
    public void firstTimeLoad() {
        interactor.fetchItems(this);
    }

    @Override
    public void loadMore() {
        interactor.loadMore(this, ((NewsListFragment) view), lastDate);
    }

    @Override
    public void onFirstLoad(LatestNewsList latestNewsList) {
        lastDate = latestNewsList.date;
        view.onNewsFetched(latestNewsList.news, latestNewsList.topNews);
    }

    @Override
    public void onMoreLoaded(LatestNewsList data) {
        lastDate = data.date;
        view.onMoreLoaded(data.news);
    }
}
