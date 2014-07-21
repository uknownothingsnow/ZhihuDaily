package app.brucelee.me.zhihudaily.ui.newsDetail;

import app.brucelee.me.zhihudaily.bean.NewsDetail;

/**
 * Created by bruce on 7/9/14.
 */
public interface NewsDetailPresenter {
    public void show(NewsDetail newsDetail);

    public void fetch();

    public void onPause();

    public void onResume();
}
