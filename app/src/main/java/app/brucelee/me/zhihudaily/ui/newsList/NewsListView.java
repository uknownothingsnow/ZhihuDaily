package app.brucelee.me.zhihudaily.ui.newsList;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.List;

import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.bean.TopNews;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by bruce on 7/8/14.
 */
public interface NewsListView extends AbsListView.OnItemClickListener, OnRefreshListener, AbsListView.OnScrollListener {
    public ListView getListView();

    public void setNewsItems(List<News> newsList, List<TopNews> topNewsList);

    public NewsAdapter getNewsAdapter();

    public Context getContext();
}
