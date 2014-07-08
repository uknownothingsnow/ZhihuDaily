package app.brucelee.me.zhihudaily.ui.newsList;

import java.util.List;

import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.bean.TopNews;

/**
 * Created by bruce on 7/8/14.
 */
public interface OnFetchedListener {
    public void onFetched(List<News> newsList, List<TopNews> topNewsList);
}
