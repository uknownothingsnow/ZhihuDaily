package app.brucelee.me.zhihudaily.interactor;

import app.brucelee.me.zhihudaily.bean.TopicList;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.support.MyAsyncTask;
import app.brucelee.me.zhihudaily.ui.topicList.OnFetchedListener;
import app.brucelee.me.zhihudaily.ui.topicList.TopicListTask;

/**
 * Created by bruce on 7/10/14.
 */
public class TopicListInteractorImpl implements TopicListInteractor {

    private ZhihuService service;

    public TopicListInteractorImpl(ZhihuService service) {
        this.service = service;
    }

    @Override
    public void fetch(OnFetchedListener<TopicList> listener) {
        new TopicListTask(listener).executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR);
    }
}
