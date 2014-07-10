package app.brucelee.me.zhihudaily.ui.topicList;

import app.brucelee.me.zhihudaily.bean.TopicList;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by bruce on 7/9/14.
 */
public interface TopicListView {
    CardListView getListView();

    void setItems(TopicList topicList);
}
