package app.brucelee.me.zhihudaily.ui.newsList;

import android.app.Activity;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.interactor.NewsListInteractor;
import app.brucelee.me.zhihudaily.ui.newsDetail.NewsDetailActivity;
import app.brucelee.me.zhihudaily.ui.OnFetchedListener;

/**
 * Created by bruce on 7/8/14.
 */
public class NewsListPresenterImpl implements NewsListPresenter, OnFetchedListener<LatestNewsList> {

    private NewsListView view;
    private Activity activity;
    private NewsListInteractor interactor;

    public NewsListPresenterImpl(NewsListView view, NewsListInteractor interactor) {
        this.view = view;
        this.activity = ((NewsListFragment) view).getActivity();
        this.interactor = interactor;
        interactor.fetchItems(this);
    }

    @Override
    public void onListItemClick(final int position) {
        News news = (News) view.getNewsAdapter().getItem(position);
        activity.startActivity(NewsDetailActivity.newIntent(view.getContext(), news.id));
    }

    @Override
    public void onFetched(LatestNewsList latestNewsList) {
        view.setNewsItems(latestNewsList.news, latestNewsList.topNews);
    }
}
