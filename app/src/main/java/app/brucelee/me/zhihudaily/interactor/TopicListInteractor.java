package app.brucelee.me.zhihudaily.interactor;

import app.brucelee.me.zhihudaily.bean.TopicList;
import app.brucelee.me.zhihudaily.ui.OnFirstLoadListener;

/**
 * Created by bruce on 7/10/14.
 */
public interface TopicListInteractor {
    void fetch(OnFirstLoadListener<TopicList> listener);
}
