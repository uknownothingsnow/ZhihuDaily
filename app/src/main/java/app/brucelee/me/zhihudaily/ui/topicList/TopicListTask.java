package app.brucelee.me.zhihudaily.ui.topicList;

import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.bean.TopicList;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.support.MyAsyncTask;
import app.brucelee.me.zhihudaily.ui.OnFetchedListener;

/**
 * Created by bruce on 7/10/14.
 */
public class TopicListTask extends MyAsyncTask<Void, Void, TopicList> {
    final OnFetchedListener<TopicList> listener;
    final ZhihuService service;

    public TopicListTask(final ZhihuService service, final OnFetchedListener<TopicList> listener) {
        this.service = service;
        this.listener = listener;
    }

    @Override
    protected TopicList doInBackground(Void... params) {
        return service.getTopicList();
    }

    @Override
    protected void onPostExecute(TopicList topicList) {
        listener.onFetched(topicList);
    }
}
