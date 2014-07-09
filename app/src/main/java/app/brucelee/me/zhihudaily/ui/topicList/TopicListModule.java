package app.brucelee.me.zhihudaily.ui.topicList;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.ui.main.MainModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/9/14.
 */
@Module(
        injects = TopicListFragment.class,
        addsTo = MainModule.class,
        library = true
)
public class TopicListModule {

    private TopicListView view;

    public TopicListModule(TopicListView view) {
        this.view = view;
    }

    @Provides @Singleton public TopicListView provideTopicListView() {
        return view;
    }

    @Provides @Singleton public TopicListPresenter provideTopicListPresenter(TopicListView view) {
        return new TopicListPresenterImpl(view);
    }
}
