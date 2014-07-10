package app.brucelee.me.zhihudaily.ui.topicList;

import app.brucelee.me.zhihudaily.bean.TopicList;
import app.brucelee.me.zhihudaily.interactor.TopicListInteractor;

/**
 * Created by bruce on 7/9/14.
 */
public class TopicListPresenterImpl implements TopicListPresenter, OnFetchedListener<TopicList> {
    private TopicListView view;
    private TopicListInteractor interactor;

    public TopicListPresenterImpl(TopicListView view, TopicListInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onFetched(TopicList data) {
        view.setItems(data);
    }

    @Override
    public void fetch() {
        interactor.fetch(this);
    }
}
