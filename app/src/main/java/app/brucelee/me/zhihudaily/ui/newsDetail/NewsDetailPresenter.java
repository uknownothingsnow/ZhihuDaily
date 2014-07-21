package app.brucelee.me.zhihudaily.ui.newsDetail;

import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.ui.IPresenter;

/**
 * Created by bruce on 7/9/14.
 */
public interface NewsDetailPresenter extends IPresenter {
    public void show(NewsDetail newsDetail);

    public void fetch();

    public void onPause();

    public void onResume();
}
