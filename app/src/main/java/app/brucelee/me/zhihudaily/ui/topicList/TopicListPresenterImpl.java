package app.brucelee.me.zhihudaily.ui.topicList;

import app.brucelee.me.zhihudaily.bean.TopicList;
import app.brucelee.me.zhihudaily.interactor.TopicListInteractor;
import app.brucelee.me.zhihudaily.ui.OnFirstLoadListener;

/**
 * Created by bruce on 7/9/14.
 */
public class TopicListPresenterImpl implements TopicListPresenter, OnFirstLoadListener<TopicList> {
    private TopicListView view;
    private TopicListInteractor interactor;

    public TopicListPresenterImpl(TopicListView view, TopicListInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onFirstLoad(TopicList data) {
        view.setItems(data);
    }

    @Override
    public void fetch() {
        interactor.fetch(this);
    }
}
