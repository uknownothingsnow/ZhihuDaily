package app.brucelee.me.zhihudaily.ui.newsList;

import android.app.Activity;

import com.google.common.collect.FluentIterable;

import java.util.List;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.interactor.NewsListInteractor;
import app.brucelee.me.zhihudaily.ui.OnMoreLoadedListener;
import app.brucelee.me.zhihudaily.ui.newsDetail.NewsDetailActivity;
import app.brucelee.me.zhihudaily.ui.OnFetchedListener;

/**
 * Created by bruce on 7/8/14.
 */
public class NewsListPresenterImpl implements NewsListPresenter, OnFetchedListener<LatestNewsList>, OnMoreLoadedListener<LatestNewsList> {

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
    public void loadMore(String date) {
        interactor.loadMore(this, ((NewsListFragment) view), "");
    }

    @Override
    public void onFetched(LatestNewsList latestNewsList) {
        view.setNewsItems(latestNewsList.news, latestNewsList.topNews);
    }

    @Override
    public void onMoreLoaded(LatestNewsList data) {

    }
}
