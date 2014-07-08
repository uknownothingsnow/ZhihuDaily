package app.brucelee.me.zhihudaily.ui.newsList;

import android.app.Activity;

import java.util.List;

import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.bean.TopNews;
import app.brucelee.me.zhihudaily.interactor.NewsListInteractor;
import app.brucelee.me.zhihudaily.ui.activity.NewsDetailActivity;

/**
 * Created by bruce on 7/8/14.
 */
public class NewsListPresenterImpl implements NewsListPresenter, OnFetchedListener {

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
        activity.startActivity(NewsDetailActivity.newIntent(news.id));
    }

    @Override
    public void onFetched(List<News> newsList, List<TopNews> topNewsList) {
        view.setNewsItems(newsList, topNewsList);
    }
}
